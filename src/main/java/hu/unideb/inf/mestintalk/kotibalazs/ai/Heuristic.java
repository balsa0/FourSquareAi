package hu.unideb.inf.mestintalk.kotibalazs.ai;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

/**
 * This interface is for AI heuristic.
 */
public interface Heuristic {

	/**
	 * This method must implemnt heuristic for a player for a given game state.
	 * @param player the player
	 * @param board the game state
	 * @return the heuristic value
	 */
	Integer calculate(Player player, GameState board);

}
