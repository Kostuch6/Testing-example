package com.learning.courses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "papers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String paperType;

    private String topic;

    private String isbn;

    private String additionalAuthors;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    private int numberOfPages;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Person tutor;

    public Paper(String title, String paperType, String topic, String isbn,
                 String additionalAuthors, Date publicationDate, int numberOfPages) {
        this.title = title;
        this.paperType = paperType;
        this.topic = topic;
        this.isbn = isbn;
        this.additionalAuthors = additionalAuthors;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
    }
}