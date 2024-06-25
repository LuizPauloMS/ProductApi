package com.ProductApi.main.exception.exceptionDTO;

import lombok.Data;

@Data
public class ProductItemError {
	   private int statusCode;
	    private String mensagem;
	    private String timestamp;

	    public ProductItemError(int statusCode, String mensagem, String timestamp) {
	        this.statusCode = statusCode;
	        this.mensagem = mensagem;
	        this.timestamp = timestamp;
	    }
}
