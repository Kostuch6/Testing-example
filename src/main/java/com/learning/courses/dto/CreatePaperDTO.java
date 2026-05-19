package com.learning.courses.dto;

import com.learning.courses.model.enums.PaperType;
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
public class CreatePaperDTO implements Serializable {

  @NotNull
  @Schema(example = "ARTICLE")
  protected PaperType type;

  @Schema(example = "978-3-16-148410-0")
  protected String isbn;

  @NotBlank
  @Schema(example = "Distributed Systems Testing")
  protected String topic;

  @Schema(example = "Jane Doe, John Smith")
  protected String additionalAuthors;

  @NotNull
  @Schema(example = "1")
  protected Long tutorId;

}
