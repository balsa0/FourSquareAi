package hu.unideb.inf.mestintalk.kotibalazs.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.exception.InvalidStepException;
import hu.unideb.inf.mestintalk.kotibalazs.exception.GameRuleViolationException;
import hu.unideb.inf.mestintalk.kotibalazs.exception.InconsistentGameStateError;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;
import hu.unideb.inf.mestintalk.kotibalazs.rule.GameRule;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class implements a the game field (board). It also represents every game state information needed for the rules.
 */
public class GameState {

	public GameState(){
		// init game
		this.initOrResetGame();
	}

	// properties

	private Table<Integer, Integer, Square> gameBoard;

	private Set<GameRule> rules;

	private List<Player> players;

	private Integer lastX;
	private Integer lastY;

	private Player activePlayer;

	private boolean endGame;

	// setters and getters

	public Table<Integer, Integer, Square> getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Table<Integer, Integer, Square> gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Set<GameRule> getRules() {
		return rules;
	}

	private void setRules(Set<GameRule> rules) {
		this.rules = rules;
	}

	public List<Player> getPlayers() {
		return players;
	}

	private void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Integer getLastX() {
		return lastX;
	}

	public void setLastX(Integer lastX) {
		this.lastX = lastX;
	}

	public Integer getLastY() {
		return lastY;
	}

	public void setLastY(Integer lastY) {
		this.lastY = lastY;
	}

	public Square getLastSquare(){
		return gameBoard.get(lastX, lastY);
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	// custom methods

	/**
	 * This method initializes or resets current game state. Should be called before the game.
	 */
	public void initOrResetGame(){
		this.lastX = null;
		this.lastY = null;
		this.activePlayer = null;
		this.gameBoard = HashBasedTable.create();
		this.rules = new LinkedHashSet<GameRule>();
		this.players = new LinkedList<Player>();
		this.endGame = false;
	}

	/**
	 * Indicates if the game is ready to start or not.
	 * @return true if the game can be started.
	 */
	public boolean isReadyToPlay(){
		return !(players == null || players.size() < 2
				|| rules == null || rules.size() == 0
				|| gameBoard == null || endGame);
	}

	/**
	 * This method register a new rule to the game. The rule will be automatically applied.
	 * @param gameRule the @{@link GameRule} to register.
	 */
	public void registerGameRule(GameRule gameRule){
		this.rules.add(gameRule);
	}

	/**
	 * This method applies registered rules on the game. This will keep the order of registration.
	 */
	private void applyRules(){
		for (GameRule rule : getRules()){
			// validate game state beforehand
			try {
				rule.preValidate(this);
			}catch (GameRuleViolationException e){
				throw new InconsistentGameStateError("Failed to validate game state BEFORE applying rule (pre-postValidate): "
						+ rule.getClass().getCanonicalName() + ". The exception was: " + e.getClass().getCanonicalName() + " with message: " + e.getMessage());
			}
			// only apply rule if condition is satisfied
			if(rule.preCondition(this)) {
				rule.apply(this);
				// validate game state after applying rule
				try {
					rule.postValidate(this);
				}catch (GameRuleViolationException e){
					throw new InconsistentGameStateError("Failed to validate game state AFTER applying rule (postValidate): "
							+ rule.getClass().getCanonicalName() + ". The exception was: " + e.getClass().getCanonicalName() + " with message: " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Registers a player for the game. If no active players set, this will set the player as the active one.
	 * @param player the player to register.
	 */
	public void registerPlayer(Player player){
		this.players.add(player);
		if(this.activePlayer == null)
			this.activePlayer = player;
	}

	/**
	 * This method called when a step made in the game.
	 * @param x x position of the new square.
	 * @param y y position of the new square.
	 * @param square the new square object.
	 * @throws GameRuleViolationException it is thrown when a game rule is violated.
	 */
	public void step(Integer x, Integer y, Square square) throws GameRuleViolationException{

		if(!isReadyToPlay())
			throw new InvalidStepException("The game is not ready to start. " +
					"You should register least two players and at least one game rule or reset the game.");

		// validate step
		if(gameBoard.contains(x,y)){
			throw new InvalidStepException("You cannot place a square on position ("
					+x+","+y+")! This cell already contains a Square.");
		}

		// updating last step information
		this.lastX = x;
		this.lastY = y;

		// adding new square
		gameBoard.put(x, y, square);
		// applying game rules
		this.applyRules();
	}



}
