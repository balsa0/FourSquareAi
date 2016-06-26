package hu.unideb.inf.mestintalk.kotibalazs.ui;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import javafx.scene.layout.GridPane;

/**
 * Created by Balsa on 2016. 06. 26..
 */
public class GameBoard extends GridPane {

	private GameState gameState;


	public GameBoard(GameState gameState) {
		this.gameState = gameState;


	}


}
