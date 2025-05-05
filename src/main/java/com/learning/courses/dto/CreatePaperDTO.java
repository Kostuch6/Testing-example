package com.learning.courses.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CreatePaperDTO implements Serializable {

    @NotBlank
    @Schema(example = "Conference Paper")
    private String type;

    @NotBlank
    @Schema(example = "978-3-16-148410-0")
    private String isbn;

    @NotBlank
    @Schema(example = "Deep Learning for Education")
    private String topic;

    @Schema(description = "Lista dodatkowych autorów (imię i nazwisko)")
    private List<String> additionalAuthors;

    @NotNull
    @Schema(example = "5", description = "ID istniejącego tutora (Person.role = TUTOR)")
    private Long tutorId;
}
