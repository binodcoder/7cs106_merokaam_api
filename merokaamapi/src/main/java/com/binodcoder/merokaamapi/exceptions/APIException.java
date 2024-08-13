package com.binodcoder.merokaamapi.exceptions;
public class APIException extends RuntimeException {
    private static final long serialVersionUId = 1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
