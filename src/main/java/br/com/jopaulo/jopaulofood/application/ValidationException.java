package br.com.jopaulo.jopaulofood.application;

@SuppressWarnings("serial")
public class ValidationException extends Exception{
	
	public ValidationException(String message) {
		super(message);
	}
}
