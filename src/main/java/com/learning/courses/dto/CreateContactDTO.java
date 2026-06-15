package com.learning.courses.dto;

import com.learning.courses.model.enums.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CreateContactDTO implements Serializable {

  @NotNull
  @Schema(example = "EMAIL")
  private ContactType type;

  @NotBlank
  @Schema(example = "student@example.com")
  private String value;

  @Schema(example = "home")
  private String label;
}
