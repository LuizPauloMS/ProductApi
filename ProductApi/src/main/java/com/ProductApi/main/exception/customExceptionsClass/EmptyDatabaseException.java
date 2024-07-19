package com.ProductApi.main.exception.customExceptionsClass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyDatabaseException extends RuntimeException {
   private static String message = "empty.data.base";
	
	public EmptyDatabaseException() {
        super(message);
    }
	
	public String getMessage() {
		return message;
	}
}
