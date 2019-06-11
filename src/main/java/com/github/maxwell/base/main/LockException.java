package com.github.maxwell.base.main;

public class LockException extends RuntimeException{
    private static final long serialVersionUID = -604869986480714150L;

    public LockException(String message) {
        super(message);
    }

    public LockException(Throwable e) {
        super(e);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
