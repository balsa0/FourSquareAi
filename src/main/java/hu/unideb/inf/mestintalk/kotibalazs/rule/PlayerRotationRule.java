package hu.unideb.inf.mestintalk.kotibalazs.rule;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

/**
 * This game rule will control the player rotation of the game field.
 */
public class PlayerRotationRule extends AbstractGameRule {

	/**
	 *
	 * @param board not required.
	 * @return
	 */
	@Override
	public boolean preCondition(GameState board) {
		return board.getPlayers() != null && board.getPlayers().size() > 1;
	}

	/**
	 * Rotates the players.
	 * @param board the game state.
	 */
	@Override
	public void apply(GameState board) {
		// rotate to the next player
		try{
			// set the next player
			board.setActivePlayer(board.getPlayers().get(board.getPlayers().indexOf(board.getActivePlayer()) + 1));
		}catch (IndexOutOfBoundsException e){
			board.setActivePlayer(board.getPlayers().get(0));
		}

		// trigger step action for player
		if(!board.isEndGame())
			board.getActivePlayer().triggerStep(board);

	}
}
