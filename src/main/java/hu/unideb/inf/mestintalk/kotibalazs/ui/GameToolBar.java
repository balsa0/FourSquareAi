package hu.unideb.inf.mestintalk.kotibalazs.ui;

import hu.unideb.inf.mestintalk.kotibalazs.ai.implementation.MaxNStepRecommender;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameStateChangeAware;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.AiPlayer;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.HumanPlayer;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the Game Control Toolbar for the game.
 */
public class GameToolBar extends ToolBar implements GameStateChangeAware{

	private GameState gameState;

	private GameBoard gameBoard;

	private ProgressIndicator progressIndicator;
	private Separator separator0;
	private Button newGameBtn;
	private Separator separator1;
	private Label mapControlsLabel;
	private Button leftBtn;
	private Button downBtn;
	private Button upBtn;
	private Button rightBtn;
	private Separator separator2;
	private Label addPlayerLabel;
	private Button addHumanPlayer;
	private Button addAiPlayer;
	private Separator separator3;
	private Label playersLabel;

	private Map<Player, Button> playerButtons;

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setProgress(boolean value){
		this.progressIndicator.setVisible(value);
	}

	public Button getNewGameBtn() {
		return newGameBtn;
	}

	public void setNewGameBtn(Button newGameBtn) {
		this.newGameBtn = newGameBtn;
	}

	public Button getLeftBtn() {
		return leftBtn;
	}

	public void setLeftBtn(Button leftBtn) {
		this.leftBtn = leftBtn;
	}

	public Button getDownBtn() {
		return downBtn;
	}

	public void setDownBtn(Button downBtn) {
		this.downBtn = downBtn;
	}

	public Button getUpBtn() {
		return upBtn;
	}

	public void setUpBtn(Button upBtn) {
		this.upBtn = upBtn;
	}

	public Button getRightBtn() {
		return rightBtn;
	}

	public void setRightBtn(Button rightBtn) {
		this.rightBtn = rightBtn;
	}

	public Button getAddHumanPlayer() {
		return addHumanPlayer;
	}

	public void setAddHumanPlayer(Button addHumanPlayer) {
		this.addHumanPlayer = addHumanPlayer;
	}

	public Button getAddAiPlayer() {
		return addAiPlayer;
	}

	public void setAddAiPlayer(Button addAiPlayer) {
		this.addAiPlayer = addAiPlayer;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameToolBar(GameState gameState){

		super();

		this.playerButtons = new HashMap<>();

		// game state
		this.gameState = gameState;

		// progress indicator
		progressIndicator = new ProgressIndicator();
		progressIndicator.setMaxHeight(25);
		progressIndicator.setPrefSize(25, 25);
		progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		progressIndicator.setVisible(false);

		separator0 = new Separator();
		separator0.setOrientation(Orientation.VERTICAL);

		// setting up toolbar buttons
		newGameBtn = new Button();
		newGameBtn.setText("New Game");

		separator1 = new Separator();
		separator1.setOrientation(Orientation.VERTICAL);

		mapControlsLabel = new Label();
		mapControlsLabel.setText("Move board: ");

		// move left button
		leftBtn = new Button();
		leftBtn.setText("←");

		// move down button
		downBtn = new Button();
		downBtn.setText("↓");

		// move up button
		upBtn = new Button();
		upBtn.setText("↑");

		// move right button
		rightBtn = new Button();
		rightBtn.setText("→");

		// separator
		separator2 = new Separator();
		separator2.setOrientation(Orientation.VERTICAL);

		// add player
		addPlayerLabel = new Label();
		addPlayerLabel.setText("Add player: ");

		// add human player
		addHumanPlayer = new Button();
		addHumanPlayer.setText("♥ Human");

		// add ai player
		addAiPlayer = new Button();
		addAiPlayer.setText("♦ Ai");

		// separator
		separator3 = new Separator();
		separator3.setOrientation(Orientation.VERTICAL);

		// players
		playersLabel = new Label();
		playersLabel.setText("Players: ");

		this.getItems().addAll(
				progressIndicator,
				separator0,
				newGameBtn,
				separator1,
				mapControlsLabel,
				leftBtn,
				downBtn,
				upBtn,
				rightBtn,
				separator2,
				addPlayerLabel,
				addHumanPlayer,
				addAiPlayer,
				separator3
		);

		this.setupCallbacks();

	}

	/**
	 * This method sets up player callbacks
	 */
	private void setupCallbacks(){

		// new game button
		newGameBtn.setOnAction(event -> {

			// remove player buttons
			playerButtons.forEach(
				(player, button) -> {
					this.getItems().remove(button);
				}
			);

			// re-init game
			gameState.initOrResetGame();

			// reset color pointer
			Player.resetColorPointer();

		});


		// add human player button
		addHumanPlayer.setOnAction(event -> {

			// creating a new player
			Player newPlayer = new HumanPlayer();

			// creating button for the new player
			Button newPlayerButton = new Button();
			newPlayerButton.setText("♥");
			newPlayerButton.setStyle("-fx-background-color: \"" + newPlayer.getPlayerColor() + "\";");

//			newPlayerButton.setDisable(true);

			// add player button
			playerButtons.put(newPlayer, newPlayerButton);

			// registering the player
			this.gameState.registerPlayer(newPlayer);

			// trigger step if player is ai
			if(gameState.getActivePlayer() != null && gameState.isReadyToPlay())
				gameState.getActivePlayer().triggerStep(this.gameState);


			// add button to the UI
			this.getItems().add(newPlayerButton);
		});

		// add ai player button
		addAiPlayer.setOnAction(event -> {

			// creating a new player
			Player newPlayer = new AiPlayer(new MaxNStepRecommender());

			// creating button for the new player
			Button newPlayerButton = new Button();
			newPlayerButton.setText("♦");
			newPlayerButton.setStyle("-fx-background-color: \"" + newPlayer.getPlayerColor() + "\";");

//			newPlayerButton.setDisable(true);

			// add player button
			playerButtons.put(newPlayer, newPlayerButton);

			// registering the player
			this.gameState.registerPlayer(newPlayer);

			// trigger step if player is ai
			if(gameState.getActivePlayer() != null && gameState.isReadyToPlay())
				gameState.getActivePlayer().triggerStep(this.gameState);

			// add button to the UI
			this.getItems().add(newPlayerButton);

		});

		leftBtn.setOnAction(event -> {
				if (gameBoard != null)
					gameBoard.moveLeft();
			}
		);

		rightBtn.setOnAction(event -> {
					if (gameBoard != null)
						gameBoard.moveRight();
				}
		);

		upBtn.setOnAction(event -> {
					if (gameBoard != null)
						gameBoard.moveUp();
				}
		);

		downBtn.setOnAction(event -> {
					if (gameBoard != null)
						gameBoard.moveDown();
				}
		);
	}

	/**
	 * This method will be called whenever gamestate changes
	 * @param gameState the actual gameState
	 */
	@Override
	public void onGameStateChanged(GameState gameState) {

		this.gameState = gameState;

		// player buttons activation
		playerButtons.forEach(
				(player, button) -> {
					button.setOpacity(player.equals(this.gameState.getActivePlayer()) ? 1 : .4);
					button.setTooltip(new Tooltip("Score: "+player.getScore()+" points"));
				}
		);

		// show indicator if active player is not a human player
		if(gameState.getActivePlayer() != null) {
			if (gameState.getActivePlayer() instanceof AiPlayer) {
				this.progressIndicator.setVisible(true);
			} else {
				this.progressIndicator.setVisible(false);
			}
		}
	}
}
