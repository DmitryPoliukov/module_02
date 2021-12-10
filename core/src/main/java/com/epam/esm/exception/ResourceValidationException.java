package com.epam.esm.exception;

public class ResourceValidationException extends Exception {
    public ResourceValidationException() {
        super();
    }

    public ResourceValidationException(String message) {
        super(message);
    }

    public ResourceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceValidationException(Throwable cause) {
        super(cause);
    }
}
