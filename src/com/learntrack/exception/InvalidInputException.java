package com.learntrack.exception;

// Thrown when the user enters something invalid (empty name, bad number, etc.)
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}