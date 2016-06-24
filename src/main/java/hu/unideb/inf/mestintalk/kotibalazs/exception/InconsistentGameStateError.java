package hu.unideb.inf.mestintalk.kotibalazs.exception;

/**
 * Created by Balsa on 2016. 06. 23..
 */
public class InconsistentGameStateError extends Error {

	public InconsistentGameStateError() {
		super();
	}

	public InconsistentGameStateError(String message) {
		super(message);
	}
}
