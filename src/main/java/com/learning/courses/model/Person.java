package com.learning.courses.model;

import com.learning.courses.model.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
  @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
  private Long id;

  private String firstName;

  private String lastName;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String identityNumber;

  @ManyToMany
  @JoinTable(name = "person_course",
      joinColumns = @JoinColumn(name = "person_id", insertable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "course_id", insertable = false, updatable = false))
  private Set<Course> assignedCourses;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
  private Set<Course> tutoringCourses;
}
