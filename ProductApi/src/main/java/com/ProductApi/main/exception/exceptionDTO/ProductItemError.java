package com.ProductApi.main.exception.exceptionDTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductItemError {
	    private int statusCode;
	    private String mensagem;
	    private String timestamp;
	    
	    public ProductItemError() {
	    	
	    }
	    public ProductItemError(int statusCode, String mensagem, String timestamp) {
	        this.statusCode = statusCode;
	        this.mensagem = mensagem;
	        this.timestamp = timestamp;
	    }
}
