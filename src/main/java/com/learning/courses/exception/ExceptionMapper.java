package com.learning.courses.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.courses.config.ErrorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ExceptionMapper {

  @Autowired
  protected ObjectMapper objectMapper;

  @Mapping(target = "errorType", expression = "java( ex.getClass().getSimpleName() )")
  @Mapping(target = "additionalData", expression = "java( objectMapper.convertValue(ex, new com.fasterxml.jackson.core.type.TypeReference<>() {}) )")
  public abstract ErrorResponseDTO toErrorResponseDTO(EntityNotFoundException ex);
}
