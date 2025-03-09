package com.learning.courses.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learning.courses.model.enums.Role;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "suppressed"})
public class InvalidRoleException extends RuntimeException {

    private final Object identifier;
    private final Role expectedRole;
    private final Role actualRole;


    public InvalidRoleException(Object identifier, Role expectedRole, Role actualRole) {
        this.identifier = identifier;
        this.expectedRole = expectedRole;
        this.actualRole = actualRole;
    }
}
