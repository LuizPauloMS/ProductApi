package com.ProductApi.main.exception.exceptionController;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ProductApi.main.exception.customExceptionsClass.EmptyDatabaseException;
import com.ProductApi.main.exception.customExceptionsClass.ProductNotFoundException;
import com.ProductApi.main.exception.exceptionDTO.ProductItemError;
import com.ProductApi.main.services.ProductService;

@ControllerAdvice
public class ControllerException {

    private final MessageSource messageSource;
    
    public ControllerException(MessageSource messageSource){
    	this.messageSource = messageSource;
    }
    
    @ExceptionHandler(EmptyDatabaseException.class)
    public ResponseEntity<ProductItemError> emptyDataBaseException(EmptyDatabaseException ex) {
    	String message = messageSource.getMessage(ex.getMessage(), null, new Locale("pt", "BR"));
    	return ResponseEntity
                  .status(getResponseStatus(ex))
                  .body(baseErrorBuilder(getResponseStatus(ex),message));
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductItemError> productNotFoundException(ProductNotFoundException ex) {
        String message = messageSource.getMessage(ex.getMessage(), null, new Locale("pt", "BR"));
    	return ResponseEntity
                 .status(getResponseStatus(ex))
                 .body(baseErrorBuilder(getResponseStatus(ex),message));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProductItemError> InvalidParameterException(IllegalArgumentException ex) {
    	String message = messageSource.getMessage(ex.getMessage(), null, new Locale("pt", "BR"));
    	return ResponseEntity
                  .status(getResponseStatus(ex))
                  .body(baseErrorBuilder(getResponseStatus(ex),message));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductItemError> genericException(Throwable ex) {
        return ResponseEntity
                .status(getResponseStatus(ex))
                .body(baseErrorBuilder(getResponseStatus(ex),ex.getMessage()));
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ProductItemError> handleNoHandlerFoundException(NoHandlerFoundException ex) {
    	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(baseErrorBuilder(HttpStatus.NOT_FOUND,"Endereço Errado"));
    }
   
    private ProductItemError baseErrorBuilder(HttpStatus httpStatus, String message) {
        return new ProductItemError(
                httpStatus.value(),
                message,
                ProductService.getCurrentDateTime()
                );
    }
    
    private HttpStatus getResponseStatus(Throwable exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        if (exception.getClass().getAnnotation(ResponseStatus.class) == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseStatus.value();
    }
}

