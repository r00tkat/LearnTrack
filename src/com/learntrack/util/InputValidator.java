package com.learntrack.util;

import com.learntrack.exception.InvalidInputException;

// Small helper to check user input before we use it.
public class InputValidator {

    private InputValidator() {
    }

    // Throws if a string is null or blank.
    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    // Throws if a number is not positive.
    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than 0.");
        }
    }
}