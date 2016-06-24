package hu.unideb.inf.mestintalk.kotibalazs.rule;

import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * This rule will check if a player scored in the game.
 */
public class SquareCheckerRule  extends AbstractGameRule{

	// score reward for a player
	private final static Integer scoreReward = 5;

	/**
	 * Simple null checking.
	 * @param board the game state.
	 * @return true if null check passes
	 */
	@Override
	public boolean preCondition(GameState board) {
		return board.getLastX() != null
				&& board.getLastY() != null
				&& board.getLastSquare() != null;
	}

	/**
	 * This method will check for new 2x2 blocks next to the newly placed square.
	 * @param board the game state.
	 */
	@Override
	public void apply(GameState board) {

		Integer count = 0;

		//offset by -1
		for(int xOffset = 0; xOffset >= -1; xOffset--){
			for(int yOffset = 0; yOffset >= -1; yOffset--) {
				boolean newSquare = true;

				// iterate trough 2 x 2 blocks and verify every block
				for(int x = board.getLastX(); x <= board.getLastX()+1; x++){
					for(int y = board.getLastY(); y <= board.getLastY()+1; y++){
						// skip current iteration if needed
						if(!newSquare)
							break;

						// determine the real coordinates
						Integer realX = x + xOffset;
						Integer realY = y + yOffset;

						// skip if no square on position
						if(!board.getGameBoard().contains(realX, realY)){
							newSquare = false;
							continue;
						}

						// check if the current square belongs to the player
						if(board.getGameBoard().get(realX, realY).getOwner() != board.getLastSquare().getOwner()){
							newSquare = false;
							break;
						}

					}
				}
				count = newSquare ? count+1 : count;
			}
		}

		// update the players score
		board.getPlayers().get(
			board.getPlayers().indexOf(board.getLastSquare().getOwner())
		).addScore(count * scoreReward);

	}

}
