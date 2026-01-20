package org.exception;

public class CustomException extends Exception {
  
	CustomException(String exceptionMessage){
		System.err.println("CustomException");
		System.err.println(exceptionMessage);
	}
}
