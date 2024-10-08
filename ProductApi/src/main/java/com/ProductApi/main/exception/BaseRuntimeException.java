package com.ProductApi.main.exception;

import java.util.Map;

public  abstract class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final Map<String, Object> mapDetails;

    public BaseRuntimeException() {
        mapDetails = null;
    }
    public BaseRuntimeException(final Map<String, Object> mapDetails) {
        this.mapDetails = mapDetails;
    }

    public abstract String getExceptionKey();

    public Map<String, Object> getMapDetails() {
        return this.mapDetails;
    }

}
