package hu.unideb.inf.mestintalk.kotibalazs.model.actor;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

/**
 * This class implements a basic player.
 */
public abstract class Player {

	// color randomization
	private static List<Color> builtInColors = Arrays.asList(
			Color.RED,
			Color.AQUA,
			Color.GREEN,
			Color.YELLOW,
			Color.VIOLET,
			Color.AZURE,
			Color.BROWN,
			Color.BLUE,
			Color.CORAL
		);

	// this will be stepped to 0 for first time
	private static int colorPointer = -1;

	public static void resetColorPointer(){
		colorPointer = -1;
	}

	/**
	 * Constuctor
	 */
	public Player(){

		//step color pointer
		colorPointer++;
		if(colorPointer >= builtInColors.size())
			colorPointer = 0;

		this.playerColor = builtInColors.get(colorPointer);
	}

	private Color playerColor = Color.WHITE;

	private Integer score = 0;

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
