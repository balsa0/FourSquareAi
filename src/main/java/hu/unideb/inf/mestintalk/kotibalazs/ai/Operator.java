package hu.unideb.inf.mestintalk.kotibalazs.ai;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * This interface is for AI operators.
 */
public interface Operator {

	/**
	 * This method is for Operator precondition.
	 * @param board the game state.
	 * @return true if the operator is applicable
	 */
	boolean applicable(GameState board);

	/**
	 * This method if for applying an operator.
	 * @param board the gamestate before applying the operator
	 * @return the new gamestate after the operator
	 */
	GameState apply(GameState board);

}
