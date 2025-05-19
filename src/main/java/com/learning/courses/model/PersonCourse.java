package com.learning.courses.model;

import com.learning.courses.model.enums.PersonCourseStatus;
import com.learning.courses.model.enums.Degree;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "person_course")
public class PersonCourse {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_course_id_seq")
  @SequenceGenerator(name = "person_course_id_seq", sequenceName = "person_course_id_seq", allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
  private Person student;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
  private Course course;

  @Enumerated(EnumType.STRING)
  private PersonCourseStatus status;

  @Enumerated(EnumType.STRING)
  private Degree degree;

  private LocalDateTime joinDate;

  private LocalDateTime finishDate;
}
