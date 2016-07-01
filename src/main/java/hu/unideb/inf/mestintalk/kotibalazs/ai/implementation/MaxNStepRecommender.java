package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import hu.unideb.inf.mestintalk.kotibalazs.ai.api.HeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Operator;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.StepRecommender;
import hu.unideb.inf.mestintalk.kotibalazs.exception.NoApplicableOperatorException;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements MaxNStepRecommender algorithm
 */
public class MaxNStepRecommender implements StepRecommender{

	List<Operator> operators;

	public MaxNStepRecommender(){
		operators = new ArrayList<>();
		operators.add(new EmptyBoardOperator());
		operators.add(new AttackOperator());
	}

	@Override
	public void processAndAct(GameState actualGameState, HeuristicProvider heuristicProvider) {

		// get only the applicable operators
		List<Operator> applicableOperators = operators.stream().filter(
				operator -> operator.applicable(actualGameState)
		).collect(Collectors.toList());

		if(applicableOperators.isEmpty()){
			throw new NoApplicableOperatorException();
		}

		if(applicableOperators.size() == 1){
			applicableOperators.get(0).apply(actualGameState);
		}
	}
}
