package com.example.handler;

/**
 * Created by alexc_000 on 2016-12-30.
 */
public class ResourceNotFoundException extends IllegalArgumentException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}
