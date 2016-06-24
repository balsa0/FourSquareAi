package hu.unideb.inf.mestintalk.kotibalazs.model.actor;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import javafx.scene.paint.Color;

/**
 * This class implements a basic player.
 */
public abstract class Player {
//
//	/**
//	 * Default constructor
//	 * @param color the deriser player color on the ui.
//	 */
//	public Player(Color color){
//		this.playerColor = color;
//	}

	private Color playerColor;

	private Integer score;

	/**
	 * Player Color on the UI.
	 * @return the color of the player.
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * This method sets player color.
	 * @param playerColor the desired player color.
	 */
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}


	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void addScore(Integer score){
		this.score += score;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Player))
			return false;

		return ((Player) obj).getPlayerColor().equals(this.getPlayerColor());
	}

	/**
	 * This method will be clalled when a new player becomes active.
	 */
	public abstract void triggerStep(GameState board);




}
