package hu.unideb.inf.mestintalk.kotibalazs.rule;

import hu.unideb.inf.mestintalk.kotibalazs.exception.GameRuleViolationException;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

import java.util.function.Predicate;

/**
 * This rule will check if any of the players won the game.
 */
public class WinnerCheckerRule extends AbstractGameRule {

	private final static Integer scoreLimit = 100;

	private final static Predicate<Player> winnerPredicate = player -> player.getScore() >= scoreLimit;

	/**
	 * Check the score limit for every player
	 * @param board the game state.
	 * @throws GameRuleViolationException
	 */
	@Override
	public void postValidate(GameState board) throws GameRuleViolationException {

		board.getPlayers().stream().filter(player -> winnerPredicate.test(player)).forEach(player -> {
			board.setActivePlayer(player);
			board.setEndGame(true);
		});
	}
}
