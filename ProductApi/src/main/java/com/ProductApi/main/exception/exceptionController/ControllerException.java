package com.ProductApi.main.exception.exceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ProductApi.main.exception.customExceptionsClass.EmptyDatabaseException;
import com.ProductApi.main.exception.customExceptionsClass.ProductNotFoundException;
import com.ProductApi.main.exception.exceptionDTO.ProductItemError;
import com.ProductApi.main.services.ProductService;

@ControllerAdvice
public class ControllerException {


    @ExceptionHandler(EmptyDatabaseException.class)
    public ResponseEntity<ProductItemError> emptyDataBaseException(EmptyDatabaseException ex) {
    	ProductItemError productError = new ProductItemError(
    		      HttpStatus.NOT_FOUND.value(),
                  ex.getMessage(),
                  ProductService.getCurrentDateTime()
          );
          return new ResponseEntity<>(productError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductItemError> productNotFoundException(ProductNotFoundException ex) {
    	 ProductItemError productError = new ProductItemError(
    			 HttpStatus.NOT_FOUND.value(),
                 ex.getMessage(),
                 ProductService.getCurrentDateTime()
         );
         return new ResponseEntity<>(productError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductItemError> genericException() {

        ProductItemError productError = new ProductItemError(
        		HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ProductService.getCurrentDateTime()
        );
        return new ResponseEntity<>(productError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

