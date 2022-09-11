package com.example.backbase.exception;

public class MovieNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
