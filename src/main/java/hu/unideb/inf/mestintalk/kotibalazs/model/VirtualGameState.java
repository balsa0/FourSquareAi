package hu.unideb.inf.mestintalk.kotibalazs.model;

import com.google.common.collect.HashBasedTable;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Heuristic;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public class VirtualGameState extends  GameState {

	private Operator operator;

	private Integer depth = 0;

	private Heuristic playerHeuristic;

	public Operator getOperator() {
		return operator;
	}

//	public VirtualGameState(GameState gameState){
//			this.lastX = null;
//			this.lastY = null;
//			this.activePlayer = null;
//			this.gameBoard = HashBasedTable.create();
//			this.players = new LinkedList<>();
//			this.endGame = false;
//			this.virtualStateFlag = false;
//
//	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Heuristic getPlayerHeuristic() {
		return playerHeuristic;
	}

	public void setPlayerHeuristic(Heuristic playerHeuristic) {
		this.playerHeuristic = playerHeuristic;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
}
