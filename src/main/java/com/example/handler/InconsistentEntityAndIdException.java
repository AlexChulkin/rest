package com.example.handler;

/**
 * Created by alexc_000 on 2016-12-30.
 */
public final class InconsistentEntityAndIdException extends IllegalArgumentException {
    public InconsistentEntityAndIdException() {
        super();
    }

    public InconsistentEntityAndIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentEntityAndIdException(String message) {
        super(message);
    }

    public InconsistentEntityAndIdException(Throwable cause) {
        super(cause);
    }
}
