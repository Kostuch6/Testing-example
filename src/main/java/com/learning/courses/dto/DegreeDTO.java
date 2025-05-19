package com.learning.courses.dto;

import com.learning.courses.model.enums.Degree;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Valid
public class DegreeDTO implements Serializable {

    @NotNull
    private Long courseId;

    @NotNull
    private Long personId;

    @NotNull
    private Degree degree;

}
