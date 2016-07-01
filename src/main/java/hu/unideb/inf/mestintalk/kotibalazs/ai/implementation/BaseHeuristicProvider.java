package hu.unideb.inf.mestintalk.kotibalazs.ai.implementation;

import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.Heuristic;
import hu.unideb.inf.mestintalk.kotibalazs.ai.api.HeuristicProvider;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;

import java.util.stream.Collector;

/**
 * This class implements base heuristic for the AI
 */
public class BaseHeuristicProvider implements HeuristicProvider {

	@Override
	public Heuristic calculate(GameState board) {
		Heuristic h = new Heuristic();
		board.getPlayers().stream().forEach(
				player -> h.put(player, calculateForPlayer(player,board))
		);
		return h;
	}

	/**
	 * This method calculates heuristic fotr a given player
	 * @param player the player
	 * @param board the game state
	 * @return the heuristic value
	 */
	public Integer calculateForPlayer(Player player, GameState board) {

		Integer sumValue = 0;

		for(Table.Cell<Integer, Integer, Square> cell : board.getGameBoard().cellSet()){
			if(cell.getValue().getOwner() == player){
				sumValue += calculateForCell(player, board, cell.getRowKey(), cell.getColumnKey());
			}
		}

		return sumValue;

	}

	/**
	 * This method calculates the heuristic for a given cell.
	 * @param player the player
	 * @param board the current gamestate
	 * @param x x position of the cell
	 * @param y y position of the cell
	 * @return
	 */
	private Integer calculateForCell(Player player, GameState board, Integer x, Integer y){
		Integer count = 0;

		//offset by -1
		for(int xOffset = 0; xOffset >= -1; xOffset--){
			for(int yOffset = 0; yOffset >= -1; yOffset--) {
				boolean newSquare = true;

				// iterate trough 2 x 2 blocks and verify every block
				for(int ox = x; ox <= x+1; ox++){
					for(int oy = y; oy <= y+1; oy++){
						// skip current iteration if needed
						if(!newSquare)
							break;

						// determine the real coordinates
						Integer realX = ox + xOffset;
						Integer realY = oy + yOffset;

						// skip if no square on position
						if(!board.getGameBoard().contains(realX, realY)){
							newSquare = false;
							continue;
						}

						// check if the current square belongs to the player
						if(board.getGameBoard().get(realX, realY).getOwner() != player){
							newSquare = false;
							break;
						}

					}
				}
				count = newSquare ? count+1 : count;
			}
		}

		return count;
	}

}
