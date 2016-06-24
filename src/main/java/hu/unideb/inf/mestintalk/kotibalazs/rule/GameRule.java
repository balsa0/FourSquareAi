package hu.unideb.inf.mestintalk.kotibalazs.rule;

import hu.unideb.inf.mestintalk.kotibalazs.exception.GameRuleViolationException;
import hu.unideb.inf.mestintalk.kotibalazs.model.GameState;

/**
 * This is an interface that every game rule must implement.
 */
public interface GameRule {

	void apply(GameState board);

	boolean preCondition(GameState board);

	void preValidate(GameState board) throws GameRuleViolationException;

	void postValidate(GameState board) throws GameRuleViolationException;

}
