package com.learning.courses.config;

import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.exception.ExceptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ExceptionMapper exceptionMapper;

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
    log.error("Entity not found: name: {}, id: {}", entityNotFoundException.getEntityName(), entityNotFoundException.getIdentifier());

    return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
        .body(exceptionMapper.toErrorResponseDTO(entityNotFoundException));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException integrityViolationException) {
    log.error(integrityViolationException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(integrityViolationException));
  }

}
