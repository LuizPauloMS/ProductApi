package com.ProductApi.main.exception.exceptionDTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ErrorDto {
	 private String message;

	 public ErrorDto() {
	 }

	 public ErrorDto(String message) {
		 this.message = message;
	 }
}
