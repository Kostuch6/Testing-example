package com.learning.courses.dto;

import com.learning.courses.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Valid
public class CreatePersonDTO implements Serializable {

  @NotBlank
  @Schema(example = "Joe")
  protected String firstName;

  @NotBlank
  @Schema(example = "Doe")
  protected String lastName;

  @NotBlank
  @Schema(example = "123456789")
  protected String identityNumber;

  @NotBlank
  @Schema(example = "TUTOR")
  protected Role role;
}
