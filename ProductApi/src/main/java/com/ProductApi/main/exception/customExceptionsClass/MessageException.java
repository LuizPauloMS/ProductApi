package com.ProductApi.main.exception.customExceptionsClass;

import java.util.Map;

public interface MessageException {
	 String getExceptionKey();
	 Map<String, Object> getMapDetails();
}
