package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Heuristic;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.HeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.StepRecommender;
import hu.unideb.inf.mestintalk.kotibalazs.exception.NoApplicableOperatorException;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.VirtualGameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements MaxNStepRecommender algorithm
 */
public class MaxNStepRecommender implements StepRecommender{

	private List<Operator> operators;

	private Integer maxDepth = 5;

	public MaxNStepRecommender(){
		operators = new ArrayList<>();
		operators.add(new EmptyBoardOperator());
		operators.add(new AttackOperator());
		operators.add(new DefenseOperator());
		operators.add(new TopScoringDefenseOperator());
	}

	@Override
	public VirtualGameState processAndAct(GameState actualGameState, HeuristicProvider heuristicProvider) {

		boolean isVirtual = false;
		if(actualGameState instanceof VirtualGameState) {
			isVirtual = true;
		}

		// get only the applicable operators
		List<Operator> applicableOperators = operators.stream().filter(
				operator -> operator.applicable(actualGameState)
		).collect(Collectors.toList());

		// apply that rule if only one available
		if(applicableOperators.size() == 1){
			applicableOperators.get(0).apply(actualGameState);
			return actualGameState.getVirtualCopy();
		}

		VirtualGameState nextState = actualGameState.getVirtualCopy();

		if(actualGameState.isEndGame()){
			nextState.setPlayerHeuristic(heuristicProvider.calculate(nextState));
			return nextState;
		}else if(isVirtual && ((VirtualGameState)actualGameState).getDepth() >= maxDepth){
			nextState.setPlayerHeuristic(heuristicProvider.calculate(nextState));
			return nextState;
		}else{
			Heuristic maxHeuristic = null;
			Operator selectedOperator = null;

			for(Operator operator : applicableOperators ){

				VirtualGameState appliedState = processAndAct(
						(VirtualGameState) operator.apply(nextState.getVirtualCopy())
						,heuristicProvider
				);

				if(maxHeuristic == null ||
						appliedState.getPlayerHeuristic().get(actualGameState.getActivePlayer()) >
								maxHeuristic.get(actualGameState.getActivePlayer())){
					maxHeuristic = appliedState.getPlayerHeuristic();
					selectedOperator = operator;
				}
			}

			if(selectedOperator != null){
				selectedOperator.apply(actualGameState);
				return actualGameState.getVirtualCopy();
			}else{
				throw new NoApplicableOperatorException("Selected operator was null!");
			}
		}




	}

}
