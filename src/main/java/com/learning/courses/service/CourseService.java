package com.learning.courses.service;

import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.exception.*;
import com.learning.courses.mapper.CourseMapper;
import com.learning.courses.model.Course;
import com.learning.courses.model.enums.CourseStatus;
import com.learning.courses.model.enums.Role;
import com.learning.courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final PersonService personService;

    @Transactional
    public Long createCourse(CreateCourseDTO createCourseDTO) {
        final Course course = courseMapper.toEntity(createCourseDTO);
        var tutor = personService.getPersonEntity(createCourseDTO.getTutorId());
        if (tutor.getRole() != Role.TUTOR) {
            throw new InvalidRoleException(createCourseDTO.getTutorId(), Role.TUTOR, tutor.getRole());
        }
        return courseRepository.save(course).getId();
    }

    @Transactional(readOnly = true)
    public CourseDTO getCourse(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
    }

    @Transactional(readOnly = true)
    public Course getCourseEntity(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO).toList();
    }

    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO updatedCourse) {
        var course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
        if (course.getStatus() != CourseStatus.CREATED) {
            throw new OngoingCourseModifyException("Can't modify ongoing course");
        }
        if (StringUtils.isNotBlank(updatedCourse.getCourseName())) {
            course.setCourseName(updatedCourse.getCourseName());
        }
        if (updatedCourse.getCourseLength() != null) {
            course.setCourseLength(updatedCourse.getCourseLength());
        }
        if (!Objects.equals(updatedCourse.getTutorId(), course.getTutor().getId())) {
            var tutor = personService.getPersonEntity(updatedCourse.getTutorId());
            if (tutor.getRole() != Role.TUTOR) {
                throw new InvalidRoleException(updatedCourse.getTutorId(), Role.TUTOR, tutor.getRole());
            }
            course.setTutor(tutor);
        }
        course = courseRepository.save(course);
        return courseMapper.toDTO(course);
    }

    @Transactional
    public CourseStatus startCourse(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
        if (CollectionUtils.isEmpty(course.getStudents())) {
            throw new EmptyCourseException("Can't start course with no students");
        }
        if (course.getStatus() != CourseStatus.CREATED) {
            throw new OngoingCourseModifyException("Can't start ongoing course");
        }
        course.setStatus(CourseStatus.IN_PROGRESS);
        return course.getStatus();
    }

    @Transactional
    public CourseStatus finishCourse(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
        if (course.getStatus() != CourseStatus.IN_PROGRESS) {
            throw new OngoingCourseModifyException("Can't finish new or finished course");
        }
        var unfinishedGraduating = course.getAssignedPersonCourse().stream().anyMatch(a -> a.getDegree() == null);
        if (unfinishedGraduating) {
            throw new MissingGradeException("There are students without grades in this course");
        }
        course.setStatus(CourseStatus.FINISHED);
        return course.getStatus();
    }

}
