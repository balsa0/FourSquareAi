package hu.unideb.inf.mestintalk.kotibalazs.rule;

import hu.unideb.inf.mestintalk.kotibalazs.exception.GameRuleViolationException;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * This is an abstract game rule, implementing the minimal functionality required by child classes.
 */
public abstract class AbstractGameRule implements GameRule{

	/**
	 * Simple implementation passes every time.
	 * @param board the game state.
	 * @return always true.
	 */
	public boolean preCondition(GameState board) {
		return true;
	}

	/**
	 * Simple validation rule for the game state given. Checks for nulls.
	 * @param board the game state to check.
	 * @throws GameRuleViolationException this exception will be thrown if validation fails.
	 */
	public void preValidate(GameState board) throws GameRuleViolationException {
		if(board.getGameBoard() == null)
			throw new GameRuleViolationException("Game state property is null: gameBoard!");
		if(board.getLastX() == null)
			throw new GameRuleViolationException("Game state property is null: lastX!");
		if(board.getLastY() == null)
			throw new GameRuleViolationException("Game state property is null: lastY!");
	}

	/**
	 * Empty implementation
	 * @param board the game state.
	 */
	public void apply(GameState board) {
		return;
	}

	/**
	 * Empty implementation
	 * @param board the game state.
	 */
	public void postValidate(GameState board) throws GameRuleViolationException {
		return;
	}
}
