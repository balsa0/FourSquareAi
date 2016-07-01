package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public class DefenseOperator implements Operator{

	/**
	 * The number of squares for player is bigger than 0
	 * @param board the game state.
	 * @return
	 */
	@Override
	public boolean applicable(GameState board) {
		return board.getGameBoard().cellSet().stream().filter(
				cell -> (cell.getValue().getOwner() == board.getActivePlayer())
		).count() > 0;
	}

	/**
	 * Step somewhere in the middle
	 * @param board the gamestate before applying the operator
	 * @return
	 */
	@Override
	public GameState apply(GameState board) {

		Table.Cell selected = null;
		Long selectedMax = Long.MIN_VALUE;

		// find my own cells
		for(Player p : board.getPlayers()){
			// getting player cells
			List<Table.Cell> myCells = board.getGameBoard().cellSet().stream().filter(
					cell -> (cell.getValue().getOwner() == p)
			).collect(Collectors.toList());

			for (Table.Cell cell : myCells){
				Long val = getSurroundingCellsValue(board, (Integer) cell.getRowKey(), (Integer) cell.getColumnKey(), p);
				if(selectedMax <= val){
					selectedMax = val;
					selected = cell;
				}
			}

		}

		if(selected == null)
			throw new NullPointerException("Some errord during operator processing! (No players?)");

		Random random = new Random();
		Integer range = 1;
		Integer smallRange = 0;
		Integer x = 0, y = 0;
		while (board.getGameBoard().get((Integer) selected.getRowKey() + x, (Integer) selected.getColumnKey() + y)  != null){
			x = random.nextInt(range);
			y = random.nextInt(range);
			smallRange++;
			if(smallRange >= 3){
				range++;
				smallRange = 0;
			}
		}

		board.step((Integer) selected.getRowKey() + x, (Integer) selected.getColumnKey() + y, new Square(board.getActivePlayer()));

		return board;

	}

	private long getSurroundingCellsValue(GameState board, Integer x, Integer y, Player p){

		// own cells
		Long myCells = IntStream.rangeClosed(0,9).filter(
				value -> {
					Integer cx = x + value / 3;
					Integer cy = y + value % 3;
					Square actSquare = board.getGameBoard().get(cx, cy);

					return actSquare != null && actSquare.getOwner() == p;
				}
		).count();

		// enemy cells
		Long enemyCells = IntStream.rangeClosed(0,9).filter(
				value -> {
					Integer cx = x + value / 3;
					Integer cy = y + value % 3;
					Square actSquare = board.getGameBoard().get(cx, cy);

					return actSquare != null && actSquare.getOwner() != p;
				}
		).count();

		return myCells - enemyCells;
	}

}
