package org.example.exceptions;

public class InvalidJsonException extends RuntimeException {

    public InvalidJsonException(String message) {
        super(message);
    }
}