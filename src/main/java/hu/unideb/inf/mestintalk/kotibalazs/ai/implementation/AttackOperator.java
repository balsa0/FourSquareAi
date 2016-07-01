package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public class AttackOperator implements Operator{

	/**
	 * The number of squares for player is 0
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

		// find my own cells
		List<Table.Cell> myCells = board.getGameBoard().cellSet().stream().filter(
				cell -> (cell.getValue().getOwner() == board.getActivePlayer())
		).collect(Collectors.toList());


		Table.Cell selected = myCells.get(0);
		Long selectedMin = Long.MIN_VALUE;

		for (Table.Cell cell : myCells){
			Long val = getSurroundingCellsValue(board, (Integer) cell.getRowKey(), (Integer) cell.getColumnKey());
			if(selectedMin >= val){
				selectedMin = val;
				selected = cell;
			}
		}

		Random random = new Random();
		Integer range = 1;
		Integer x = 0, y = 0;
		while (board.getGameBoard().get((Integer) selected.getRowKey() + x, (Integer) selected.getColumnKey() + y)  != null){
			x = random.nextInt(range);
			y = random.nextInt(range);
			range++;
		}

		board.step((Integer) selected.getRowKey() + x, (Integer) selected.getColumnKey() + y, new Square(board.getActivePlayer()));

		return board;

	}

	private long getSurroundingCellsValue(GameState board, Integer x, Integer y){

		// own cells
		Long myCells = IntStream.rangeClosed(0,9).filter(
				value -> {
					Integer cx = x + value / 3;
					Integer cy = y + value % 3;
					Square actSquare = board.getGameBoard().get(cx, cy);

					return actSquare != null && actSquare.getOwner() == board.getActivePlayer();
				}
		).count();

		Long enemyCells = IntStream.rangeClosed(0,9).filter(
				value -> {
					Integer cx = x + value / 3;
					Integer cy = y + value % 3;
					Square actSquare = board.getGameBoard().get(cx, cy);

					return actSquare != null && actSquare.getOwner() != board.getActivePlayer();
				}
		).count();

		return myCells - enemyCells;
	}

}
