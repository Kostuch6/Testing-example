package com.learning.courses.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class CreateCourseDTO implements Serializable {

    @NotBlank
    @Schema(example = "Mathematics")
    private String courseName;

    @NotBlank
    @Schema(example = "15")
    private Integer courseLength;

    @NotBlank
    private Long tutorId;

}
