package com.blogapis.exception;

public class ApiException extends RuntimeException {

    // Default constructor
    public ApiException() {
        super();
    }

    // Constructor with message
    public ApiException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public ApiException(Throwable cause) {
        super(cause);
    }
}
