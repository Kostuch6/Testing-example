package com.learning.courses.service;

import com.learning.courses.dto.DegreeDTO;
import com.learning.courses.exception.*;
import com.learning.courses.model.PersonCourse;
import com.learning.courses.model.enums.CourseStatus;
import com.learning.courses.model.enums.PersonCourseStatus;
import com.learning.courses.model.enums.Role;
import com.learning.courses.repository.PersonCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonCourseService {

    private final PersonCourseRepository personCourseRepository;
    private final PersonService personService;
    private final CourseService courseService;

    @Transactional
    public void assignPersonToCourse(Long courseId, Long personId) {
        var course = courseService.getCourseEntity(courseId);
        var person = personService.getPersonEntity(personId);

        if(course.getStatus() != CourseStatus.CREATED) {
            throw new OngoingCourseJoinException("Can't join ongoing course");
        }
        if(person.getRole() != Role.STUDENT) {
            throw new InvalidRoleException(personId,Role.STUDENT, person.getRole());
        }

        var personCourse = PersonCourse.builder()
                .course(course)
                .student(person)
                .status(PersonCourseStatus.JOINED)
                .joinDate(LocalDateTime.now())
                .build();
        personCourseRepository.save(personCourse);
    }

    @Transactional
    public void gradeStudent(DegreeDTO degreeDTO) {
        var course = courseService.getCourseEntity(degreeDTO.getCourseId());
        if (course.getStatus() != CourseStatus.IN_PROGRESS) {
            throw new InactiveCourseModifyException("Can't grade students in inactive (Created or Finished) course");
        }
        var pc = personCourseRepository.findByStudent_IdAndCourse_Id(degreeDTO.getPersonId(), degreeDTO.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException(List.of(degreeDTO.getPersonId(), degreeDTO.getCourseId()), PersonCourse.class.getSimpleName()));
        pc.setDegree(degreeDTO.getDegree());
        pc.setFinishDate(LocalDateTime.now());
        personCourseRepository.save(pc);
    }

}
