package hu.unideb.inf.mestintalk.kotibalazs.model.actor;

import hu.unideb.inf.mestintalk.kotibalazs.ai.implementation.PositionBasedHeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.HeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.StepRecommender;
import hu.unideb.inf.mestintalk.kotibalazs.exception.InconsistentGameStateError;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import javafx.application.Platform;

/**
 * This class implements an Ai player
 */
public class AiPlayer extends Player{

	private StepRecommender stepRecommender;
	private HeuristicProvider heuristicProvider;

	public AiPlayer(StepRecommender stepRecommender){
		this.stepRecommender = stepRecommender;
		this.heuristicProvider = new PositionBasedHeuristicProvider();
	}

	@Override
	public void triggerStep(GameState board) {
		// check if state is valid
		if(!board.isReadyToPlay()
				|| board.getActivePlayer() == null
				|| board.getActivePlayer() != this) {
			throw new InconsistentGameStateError("Ai player was triggered without a valid game state!");
		}

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {}

		// step if possible
		Platform.runLater(
				() -> stepRecommender.processAndAct(board, heuristicProvider)
		);
	}
}
