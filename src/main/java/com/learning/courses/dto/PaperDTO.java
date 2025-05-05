package com.learning.courses.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class PaperDTO implements Serializable {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Conference Paper")
    private String type;

    @Schema(example = "978-3-16-148410-0")
    private String isbn;

    @Schema(example = "Deep Learning for Education")
    private String topic;

    private List<String> additionalAuthors;

    @Schema(description = "ID tutora")
    private Long tutorId;

    @Schema(description = "Imię i nazwisko tutora")
    private String tutorName;
}

