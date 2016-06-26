package hu.unideb.inf.mestintalk.kotibalazs.ui;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AppWindow extends Application {

	private GameState gameState;

	// toolbar parts
	private GameToolBar gameToolBar;

	// game board parts
	private GameBoard gameBoard;

	/**
	 * Sets up a new game
	 */
	private void setupGame(){
		gameState = new GameState();
	}

	/**
	 * This function will set up the game window.
	 * @return this function will return with a set up scene.
	 */
	private Scene setupWindow(){

		// toolbar
		gameToolBar = new GameToolBar(gameState);
		// register the toolbar for change events
		gameState.registerChangeAwareClass(gameToolBar);

		// game board
		gameBoard = new GameBoard(gameState);

		// root layout
		VBox root = new VBox();

		// setting up top toolbar
		root.getChildren().add(gameToolBar);

		// setting up the game board
		root.getChildren().add(gameBoard);

		// returning with new scene
		return new Scene(root, 800, 600);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// setting title
		primaryStage.setTitle("FourSquare Infinity");

		// setting the icon
		primaryStage.getIcons().add( new Image(getClass().getClassLoader().getResourceAsStream( "icon.png" )));
		primaryStage.getIcons().add( new Image(getClass().getClassLoader().getResourceAsStream( "icon48.png" )));
		primaryStage.getIcons().add( new Image(getClass().getClassLoader().getResourceAsStream( "icon24.png" )));

		// setting up a game
		setupGame();

		// setting stage
		primaryStage.setScene(setupWindow());
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}