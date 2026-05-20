package com.learning.courses.model;

import com.learning.courses.model.enums.PaperType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Enumerated(EnumType.STRING)
  private PaperType type;

  private String isbn;

  private String topic;

  private String additionalAuthors;

  @ManyToOne
  @JoinColumn(name = "tutor_id", nullable = false)
  private Person tutor;

}
