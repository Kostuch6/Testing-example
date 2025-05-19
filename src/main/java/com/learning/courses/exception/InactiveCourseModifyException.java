package com.learning.courses.exception;

public class InactiveCourseModifyException extends RuntimeException {
    public InactiveCourseModifyException(String message) {
        super(message);
    }
}
