package com.ProductApi.main.exception.customExceptionsClass;

public class EmptyDatabaseException extends RuntimeException {
   
	public EmptyDatabaseException(String message) {
        super(message);
    }
}
