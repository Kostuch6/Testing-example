package com.learning.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaperDTO {

    private String title;
    private String paperType;
    private String topic;
    private String isbn;
    private String additionalAuthors;
    private Date publicationDate;
    private int numberOfPages;
    private Long tutorId;
}