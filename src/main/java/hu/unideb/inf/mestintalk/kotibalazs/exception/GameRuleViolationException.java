package hu.unideb.inf.mestintalk.kotibalazs.exception;

/**
 * This exception indicates violation of a game rule.
 */
public class GameRuleViolationException extends BaseException {

	public GameRuleViolationException() {
		super();
	}

	public GameRuleViolationException(String message) {
		super(message);
	}
}
