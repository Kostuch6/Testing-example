package com.learning.courses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paper_id_seq")
    @SequenceGenerator(name = "paper_id_seq", sequenceName = "paper_id_seq", allocationSize = 1)
    private Long id;

    private String type;

    private String isbn;

    private String topic;

    @ElementCollection
    @CollectionTable(
            name = "paper_additional_authors",
            joinColumns = @JoinColumn(name = "paper_id")
    )
    @Column(name = "author_name")
    private List<String> additionalAuthors;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Person tutor;
}
