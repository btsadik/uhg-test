package com.odx.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

	public InvalidInputException(){
		super("Invalid input");
	}
	public InvalidInputException(String message){
		super(message);
	}
}
