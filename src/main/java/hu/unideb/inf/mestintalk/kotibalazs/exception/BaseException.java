package hu.unideb.inf.mestintalk.kotibalazs.exception;

/**
 * Created by Balsa on 2016. 06. 23..
 */
public abstract class BaseException extends RuntimeException {

	public BaseException(){
		super();
	}

	public BaseException(String message){
		super(message);
	}

}
