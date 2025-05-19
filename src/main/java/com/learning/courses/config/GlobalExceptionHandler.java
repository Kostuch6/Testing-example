package com.learning.courses.config;

import com.learning.courses.exception.*;
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

  @ExceptionHandler(InvalidRoleException.class)
  public ResponseEntity<ErrorResponseDTO> handleInvalidRoleException(InvalidRoleException invalidRoleException) {
    log.error(invalidRoleException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(invalidRoleException));
  }

  @ExceptionHandler(OngoingCourseModifyException.class)
  public ResponseEntity<ErrorResponseDTO> handleOngoingCourseModifyException(OngoingCourseModifyException courseModifyException) {
    log.error(courseModifyException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(courseModifyException));
  }

  @ExceptionHandler(OngoingCourseJoinException.class)
  public ResponseEntity<ErrorResponseDTO> handleOngoingCourseJoinException(OngoingCourseJoinException courseJoinException) {
    log.error(courseJoinException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(courseJoinException));
  }

  @ExceptionHandler(EmptyCourseException.class)
  public ResponseEntity<ErrorResponseDTO> handleEmptyCourseException(EmptyCourseException emptyCourseException) {
    log.error(emptyCourseException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(emptyCourseException));
  }

  @ExceptionHandler(InactiveCourseModifyException.class)
  public ResponseEntity<ErrorResponseDTO> handleInactiveCourseModifyException(InactiveCourseModifyException inactiveCourseModifyException) {
    log.error(inactiveCourseModifyException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(inactiveCourseModifyException));
  }

  @ExceptionHandler(MissingGradeException.class)
  public ResponseEntity<ErrorResponseDTO> handleMissingGradeException(MissingGradeException missingGradeException) {
    log.error(missingGradeException.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(exceptionMapper.toErrorResponseDTO(missingGradeException));
  }

}
