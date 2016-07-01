package hu.unideb.inf.mestintalk.kotibalazs.exception;

/**
 * This exception indicates violation of a game rule.
 */
public class NoApplicableOperatorException extends BaseException {

	public NoApplicableOperatorException() {
		super();
	}

	public NoApplicableOperatorException(String message) {
		super(message);
	}
}
