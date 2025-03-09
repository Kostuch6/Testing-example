package com.learning.courses.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.courses.config.ErrorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@Mapper
public abstract class ExceptionMapper {

  @Autowired
  protected ObjectMapper objectMapper;

  @Mapping(target = "errorType", expression = "java( ex.getClass().getSimpleName() )")
  @Mapping(target = "additionalData", expression = "java( objectMapper.convertValue(ex, new com.fasterxml.jackson.core.type.TypeReference<>() {}) )")
  @Mapping(target = "message", expression = "java( ex.getMessage() )")
  public abstract ErrorResponseDTO toErrorResponseDTO(EntityNotFoundException ex);

  @Mapping(target = "additionalData", ignore = true)
  @Mapping(target = "errorType", expression = "java( ex.getClass().getSimpleName() )")
  @Mapping(target = "message", expression = "java( ex.getMessage() )")
  public abstract ErrorResponseDTO toErrorResponseDTO(DataIntegrityViolationException ex);

  @Mapping(target = "additionalData", ignore = true)
  @Mapping(target = "errorType", expression = "java( ex.getClass().getSimpleName() )")
  @Mapping(target = "message", expression = "java( \"Invalid role: \" + ex.getActualRole() + \". Expected role is: \" + ex.getExpectedRole() )")
  public abstract ErrorResponseDTO toErrorResponseDTO(InvalidRoleException ex);

  @Mapping(target = "additionalData", ignore = true)
  @Mapping(target = "errorType", expression = "java( ex.getClass().getSimpleName() )")
  @Mapping(target = "message", expression = "java( ex.getMessage() )")
  public abstract ErrorResponseDTO toErrorResponseDTO(OngoingCourseModifyException ex);

}
