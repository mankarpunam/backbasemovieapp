package com.example.backbase.exception;

public class MovieException extends RuntimeException {
    private static final long SerialVersionUID = 1L;

    public MovieException() {
    }

    public MovieException(String message) {
        super(message);
    }

    public MovieException(String message, Throwable causes) {
        super(message, causes);
    }
}
