package hu.unideb.inf.mestintalk.kotibalazs.ui;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameStateChangeAware;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.HumanPlayer;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.stream.IntStream;

/**
 * Created by Balsa on 2016. 06. 26..
 */
public class GameBoard extends GridPane implements GameStateChangeAware{

	private GameState gameState;

	private Table<Integer, Integer, Button> grid;

	Integer xOffset;
	Integer yOffset;

	public GameBoard(GameState gameState) {

		this.gameState = gameState;

		this.xOffset = 0;
		this.yOffset = 0;

		this.setGridLinesVisible(true);

		// creating table
		grid = HashBasedTable.create();

		// filling grid
		IntStream.rangeClosed(0,20).forEach(
				x -> IntStream.rangeClosed(0,20).forEach(
						y -> {
							Button btn = new Button();
							btn.setStyle("-fx-background-color: white; -fx-border-color: #CCC;");
							btn.setPrefSize(100,100);
							btn.setTextOverrun(OverrunStyle.CLIP);
							btn.setPadding(new Insets(0,-5,0,-5));
							this.add(btn,x,y);
							grid.put(x, y, btn);
						}
				)
		);
	}

	@Override
	public void onGameStateChanged(GameState gameState) {
		this.gameState = gameState;
		// reset when no players (on game reset)
		if(gameState.getPlayers().isEmpty()){
			this.xOffset = 0;
			this.yOffset = 0;
		}

		// redrawing grid
		updateGrid(gameState);
	}

	public void updateGrid(GameState gameState){
		// filling grid
		grid.cellSet().stream().forEach(
				cell ->  {
					// calculating real position
					Integer realX = cell.getRowKey() + xOffset;
					Integer realY = cell.getColumnKey() + yOffset;
					// getting the actual square (can be null if cell is empty)
					Square actualSquare = gameState.getGameBoard().get(realX, realY);
					// getting corresponding button
					Button btn = cell.getValue();
					// remove text for all buttons
					btn.setText("");

					// setting buttons state and style
					if(actualSquare == null && gameState.isReadyToPlay() && gameState.getActivePlayer() instanceof HumanPlayer){
						// the cell is empty and player is human player
						btn.setDisable(false);
						btn.setStyle("-fx-background-color: white; -fx-border-color: "+gameState.getActivePlayer()+";");
						btn.setOnAction(
								action -> {
									// disable the button
									btn.setDisable(true);
									// set color
									btn.setStyle("-fx-background-color: "+gameState.getActivePlayer()+"; -fx-border-color: "+gameState.getActivePlayer()+";");
									// animate
									new Timeline(
											new KeyFrame(Duration.seconds(0), new KeyValue(btn.opacityProperty(), .1)),
											new KeyFrame(Duration.seconds(1), new KeyValue(btn.opacityProperty(), 1))).play();
									// get old score
									Player player = gameState.getActivePlayer();
									Integer preScore = player.getScore();
									// do the step
									gameState.step(realX,realY,new Square(gameState.getActivePlayer()));
									// update grid
									updateGrid(gameState);
									// calculate score
									Integer scoreDiff = player.getScore() - preScore;
									// set text if needed
									if(scoreDiff > 0)
										btn.setText("+"+scoreDiff);
								}
						);
						btn.setOnMouseEntered(
								e -> {
									btn.setStyle("-fx-background-color: "+gameState.getActivePlayer()+"; -fx-border-color: "+gameState.getActivePlayer()+";");
								}
						);
						btn.setOnMouseExited(
								e -> {
									if(!btn.isDisabled())
										btn.setStyle("-fx-background-color: white; -fx-border-color: "+gameState.getActivePlayer()+";");
								}
						);
					}else if(actualSquare == null){
						// game is not ready or player is not human player
						btn.setDisable(true);
						btn.setStyle("-fx-background-color: white; -fx-border-color: "+gameState.getActivePlayer()+";");
					}else{
						btn.setDisable(true);
						btn.setStyle("-fx-background-color: "+actualSquare.getOwner()+"; -fx-border-color: "+actualSquare.getOwner()+";");
					}
				}
		);

	}

	public void moveLeft(){
		this.yOffset--;
		updateGrid(this.gameState);
	}

	public void moveRight(){
		this.yOffset++;
		updateGrid(this.gameState);
	}

	public void moveUp(){
		this.xOffset--;
		updateGrid(this.gameState);
	}

	public void moveDown(){
		this.xOffset++;
		updateGrid(this.gameState);
	}

}
