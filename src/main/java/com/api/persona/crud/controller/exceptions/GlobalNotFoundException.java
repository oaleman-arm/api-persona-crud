package com.api.persona.crud.controller.exceptions;

public class GlobalNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -7687460666747879355L;

    public GlobalNotFoundException() {
    }

    public GlobalNotFoundException(String message) {
        super(message);
    }

    public GlobalNotFoundException(Throwable cause) {
        super(cause);
    }

    public GlobalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
