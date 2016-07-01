package hu.unideb.inf.mestintalk.kotibalazs.ai.api;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

/**
 * This interface is for AI heuristic.
 */
public interface HeuristicProvider {

	/**
	 * This method must implemnt heuristic for a player for a given game state.
	 * @param board the game state
	 * @return the heuristic value
	 */
	Heuristic calculate(GameState board);

}
