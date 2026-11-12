package com.learning.courses.exception;

public class MissingGradeException extends RuntimeException {
    public MissingGradeException(String message) {
        super(message);
    }
}
