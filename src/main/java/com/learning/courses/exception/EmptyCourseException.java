package com.learning.courses.exception;

public class EmptyCourseException extends RuntimeException {
    public EmptyCourseException(String message) {
        super(message);
    }
}
