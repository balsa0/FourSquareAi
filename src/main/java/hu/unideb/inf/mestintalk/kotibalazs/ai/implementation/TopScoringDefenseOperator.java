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
 * This operator will attack the winning player
 */
public class TopScoringDefenseOperator implements Operator{

	/**
	 * The number of squares for player is bigger than 0
	 * @param board the game state.
	 * @return
	 */
	@Override
	public boolean applicable(GameState board) {
		return board.getGameBoard().cellSet().stream().filter(
				cell -> (cell.getValue().getOwner() == board.getActivePlayer())
		).count() > 0 && getTopScoringPlayer(board) != board.getActivePlayer();
	}

	/**
	 * Step somewhere in the middle
	 * @param board the gamestate before applying the operator
	 * @return
	 */
	@Override
	public GameState apply(GameState board) {

		Player topPlayer = getTopScoringPlayer(board);

		List<Table.Cell> myCells = board.getGameBoard().cellSet().stream().filter(
				cell -> (cell.getValue().getOwner() == topPlayer)
		).collect(Collectors.toList());


		Table.Cell selected = myCells.get(0);
		Long selectedMax = Long.MIN_VALUE;

		for (Table.Cell cell : myCells){
			Long val = getSurroundingCellsValue(board, (Integer) cell.getRowKey(), (Integer) cell.getColumnKey(), topPlayer);
			if(selectedMax <= val){
				selectedMax = val;
				selected = cell;
			}
		}


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

	private Player getTopScoringPlayer(GameState board){
		Integer maxScore = Integer.MIN_VALUE;
		Player selected = board.getPlayers().get(0);
		for (Player p : board.getPlayers()){
			if(p.getScore() > maxScore){
				maxScore = p.getScore();
				selected = p;
			}
		}
		return selected;
	}

}
