package hu.unideb.inf.mestintalk.kotibalazs.ai;

import com.google.common.collect.Table;
import hu.unideb.inf.mestintalk.kotibalazs.model.board.Square;

/**
 * This class implements a state of a MaxN run cycle.
 */
public class MaxNResult {

	private Table.Cell<Integer, Integer, Square> state;

	private boolean endState;

	private Integer value;

	public MaxNResult(Table.Cell<Integer, Integer, Square> state, boolean endState, Integer value){
		this.state = state;
		this.endState = endState;
		this.value = value;
	}

	public Table.Cell<Integer, Integer, Square> getState() {
		return state;
	}

	public void setState(Table.Cell<Integer, Integer, Square> state) {
		this.state = state;
	}

	public boolean isEndState() {
		return endState;
	}

	public void setEndState(boolean endState) {
		this.endState = endState;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
