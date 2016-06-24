package hu.unideb.inf.mestintalk.kotibalazs.ai;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.AiPlayer;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * This class implements MaxN algorithm
 */
public class MaxN {

	private static List<Operator> operators = new LinkedList<>();

	private static void registerOperator(Operator operator){
		operators.add(operator);
	}

	public static MaxNResult maxN(GameState board, AiPlayer player, Heuristic heuristic, Integer depth){

		if(depth == 0){
			heuristic.calculate(player, board);
		}

		return new MaxNResult(null, true, null);
	}


}
