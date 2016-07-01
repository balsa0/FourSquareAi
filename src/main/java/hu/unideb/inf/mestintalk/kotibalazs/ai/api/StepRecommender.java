package hu.unideb.inf.mestintalk.kotibalazs.ai.api;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public interface StepRecommender {

	void processAndAct(GameState actualGameState, HeuristicProvider heuristicProvider);

}
