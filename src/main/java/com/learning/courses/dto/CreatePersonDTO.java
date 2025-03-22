package com.learning.courses.dto;

import com.learning.courses.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
@Valid
public class CreatePersonDTO implements Serializable {

  @NotNull
  @Schema(example = "Joe")
  protected String firstName;

  @NotBlank
  @Schema(example = "Doe")
  protected String lastName;

  @NotBlank
  @Schema(example = "ABC-123456")
  protected String identityNumber;

  @NotNull
  @Schema(example = "TUTOR")
  protected Role role;
}
