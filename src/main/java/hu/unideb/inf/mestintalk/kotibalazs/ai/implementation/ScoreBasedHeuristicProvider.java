package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Heuristic;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.HeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * Created by balazs.koti on 01/07/2016.
 */
public class ScoreBasedHeuristicProvider implements HeuristicProvider {

	@Override
	public Heuristic calculate(GameState board) {
		Heuristic result = new Heuristic();
		board.getPlayers().stream().forEach(
				player -> result.put(player, player.getScore())
		);
		return result;
	}
}
