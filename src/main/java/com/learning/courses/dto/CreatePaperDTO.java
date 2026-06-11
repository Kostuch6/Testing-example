package com.learning.courses.dto;

import com.learning.courses.model.enums.PaperType;
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
public class CreatePaperDTO implements Serializable {

    @NotBlank
    @Schema(example = "Introduction to Machine Learning")
    private String title;

    @NotNull
    @Schema(example = "ARTICLE")
    private PaperType type;

    @NotBlank
    @Schema(example = "Computer Science")
    private String topic;

    @NotBlank
    @Schema(example = "978-3-16-148410-0")
    private String isbn;

    @NotBlank
    @Schema(example = "John Smith, Jane Doe")
    private String additionalAuthors;

    @NotNull
    @Schema(example = "2024")
    private Integer publicationYear;

    @NotNull
    private Long tutorId;
}
