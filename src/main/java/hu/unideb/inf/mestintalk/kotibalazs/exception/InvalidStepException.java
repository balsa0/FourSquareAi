package hu.unideb.inf.mestintalk.kotibalazs.exception;

/**
 * Created by Balsa on 2016. 06. 23..
 */
public class InvalidStepException extends GameRuleViolationException {

	public InvalidStepException() {
		super();
	}

	public InvalidStepException(String message) {
		super(message);
	}
}
