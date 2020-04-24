package br.com.jopaulo.jopaulofood;

@SuppressWarnings("serial")
public class ValidationException extends Exception{
	
	public ValidationException(String message) {
		super(message);
	}
}
