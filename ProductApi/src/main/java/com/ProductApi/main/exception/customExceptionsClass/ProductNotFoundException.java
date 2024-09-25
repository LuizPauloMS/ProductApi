package com.ProductApi.main.exception.customExceptionsClass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static String message = "product.not.found";

	public ProductNotFoundException() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}
}
