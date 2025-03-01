package com.learning.courses.config;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDTO {

  private String errorType;
  private Map<String, Object> additionalData;
  private String message;
}
