package com.learning.courses.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class Paper {

    @NotNull
    String TypeOfPaper;

    @NotEmpty
    List<Person> Authors ;

    //13 cyfr
    @Pattern(regexp = "^\\d{13}$")
    String ISBN;

    @NotEmpty
    String Topic;

    String Content;

}

