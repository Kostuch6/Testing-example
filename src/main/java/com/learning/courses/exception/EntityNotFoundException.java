package com.learning.courses.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "suppressed"})
public class EntityNotFoundException extends RuntimeException {

  private final Object identifier;
  private final String entityName;


  public EntityNotFoundException(Object identifier, String entityName) {
    this.identifier = identifier;
    this.entityName = entityName;
  }
}
