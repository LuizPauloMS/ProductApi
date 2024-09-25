package com.ProductApi.main.exception.customExceptionsClass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidAlgorithmParameterException extends RuntimeException {

	  private static final long serialVersionUID = 1L;
	  private final String message = "argument.invalid";
	  public InvalidAlgorithmParameterException() {
		super();
	  }
	  @Override
	public String getMessage() {
			return message;
	 }
}
