package hu.unideb.inf.mestintalk.kotibalazs.model;

/**
 * The classes that depend on game state changes should implement this interface
 */
public interface GameStateChangeAware {

	public void onGameStateChanged(GameState gameState);

}
