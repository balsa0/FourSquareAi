package hu.unideb.inf.mestintalk.kotibalazs.model.board;

import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

/**
 * This class implements a square placed in a square
 */
public class Square {

	private Player owner;

	/**
	 * Owner of a given square.
	 * @return
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * This method will set owner of the square.
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
