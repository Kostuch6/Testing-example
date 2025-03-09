package com.learning.courses.model;

import com.learning.courses.model.enums.CourseStatus;
import jakarta.persistence.*;

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

  @Enumerated(EnumType.STRING)
  private CourseStatus status;

  @ManyToOne
  @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false)
  private Person tutor;

  @ManyToMany(mappedBy = "assignedCourses")
  private Set<Person> assignedStudents;
}
