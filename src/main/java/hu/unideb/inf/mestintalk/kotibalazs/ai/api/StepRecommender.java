package hu.unideb.inf.mestintalk.kotibalazs.ai.api;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.VirtualGameState;

/**
 * Created by Balsa on 2016. 07. 01..
 */
public interface StepRecommender {

	VirtualGameState processAndAct(GameState actualGameState, HeuristicProvider heuristicProvider);

}
