package com.innowise.arraytask.exception;

public class CustomArrayException extends Exception {
    public CustomArrayException(String message) {
        super(message);
    }

    public CustomArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomArrayException(Throwable cause) {
        super(cause);
    }

    public CustomArrayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
