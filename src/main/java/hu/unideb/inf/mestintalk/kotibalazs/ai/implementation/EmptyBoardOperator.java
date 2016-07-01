package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;

import java.util.Random;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public class EmptyBoardOperator implements Operator{

	/**
	 * The number of squares for player is 0
	 * @param board the game state.
	 * @return
	 */
	@Override
	public boolean applicable(GameState board) {
		return (board.getGameBoard().cellSet().stream().filter(
				cell -> (cell.getValue().getOwner() == board.getActivePlayer())
		).count() == 0);
	}

	/**
	 * Step somewhere in the middle
	 * @param board the gamestate before applying the operator
	 * @return
	 */
	@Override
	public GameState apply(GameState board) {

		Random rand = new Random();
		int x = 0;
		int y = 0;
		// try to find an empty spot in a given radius
		do{
			x = rand.nextInt(board.getPlayers().size() * 5);
			y = rand.nextInt(board.getPlayers().size() * 5);
		}while (board.getGameBoard().get(x,y) != null);

		// step
		board.step(x,y, new Square(board.getActivePlayer()));
		return board;
	}
}
