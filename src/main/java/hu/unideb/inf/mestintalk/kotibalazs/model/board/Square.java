package hu.unideb.inf.mestintalk.kotibalazs.model.board;

import hu.unideb.inf.mestintalk.kotibalazs.exception.InvalidSqaureException;
import hu.unideb.inf.mestintalk.kotibalazs.model.actor.Player;

/**
 * This class implements a square placed in a square
 */
public class Square {

	private Player owner;

	public Square(Player owner){
		if(owner == null){
			throw new InvalidSqaureException("Can not create a square without an owner!");
		}
		this.owner = owner;
	}

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
