package com.learntrack.exception;

// Thrown when a student/course/enrollment with a given id does not exist.
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);   // pass the message up to the built-in Exception
    }
}