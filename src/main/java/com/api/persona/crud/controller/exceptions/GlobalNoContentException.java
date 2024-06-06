package com.api.persona.crud.controller.exceptions;

public class GlobalNoContentException extends RuntimeException {
    private static final long serialVersionUID = -7687460666747879355L;

    public GlobalNoContentException() {
    }

    public GlobalNoContentException(String message) {
        super(message);
    }

    public GlobalNoContentException(Throwable cause) {
        super(cause);
    }

    public GlobalNoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalNoContentException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
