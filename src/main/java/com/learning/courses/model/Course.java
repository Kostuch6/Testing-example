package com.learning.courses.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
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
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
  @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
  private Long id;

  private String courseName;

  private Integer courseLength;

  @ManyToOne
  @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  private Person tutor;

  @ManyToMany(mappedBy = "assignedCourses")
  private Set<Person> assignedStudents;
}
