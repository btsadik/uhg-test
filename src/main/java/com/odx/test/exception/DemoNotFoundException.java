package com.odx.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class DemoNotFoundException extends RuntimeException {
	public DemoNotFoundException(){
		super("Demo data does not exist");
	}
	public DemoNotFoundException(String message){
		super(message);
	}
}
