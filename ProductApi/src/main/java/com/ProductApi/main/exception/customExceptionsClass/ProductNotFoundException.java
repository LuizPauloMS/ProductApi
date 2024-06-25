package com.ProductApi.main.exception.customExceptionsClass;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
