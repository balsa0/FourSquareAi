package hu.unideb.inf.mestintalk.kotibalazs.model;

import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;

import java.util.List;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public class VirtualGameState extends  GameState {

	private Operator operator;

	private List<Integer> playerHeuristic;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Integer> getPlayerHeuristic() {
		return playerHeuristic;
	}

	public void setPlayerHeuristic(List<Integer> playerHeuristic) {
		this.playerHeuristic = playerHeuristic;
	}
}
