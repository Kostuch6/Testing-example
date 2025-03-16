package com.learning.courses.repository;

import com.learning.courses.model.PersonCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonCourseRepository extends JpaRepository<PersonCourse, Long> {

    Optional<PersonCourse> findByStudent_IdAndCourse_Id(Long studentId, Long courseId);

}
