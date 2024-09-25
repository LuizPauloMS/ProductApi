package com.ProductApi.main.exception.exceptionDTO;

import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductItemError {
	    private int status;
	    private String code;
	    private String timestamp;
	    private Set<ErrorDto> errors;
	    public ProductItemError() {

	    }
	    public ProductItemError(int status, String code, String timestamp, Set<ErrorDto> errors) {
	        this.status = status;
	        this.code = code;
	        this.timestamp = timestamp;
	        this.errors = errors;
	    }
}
