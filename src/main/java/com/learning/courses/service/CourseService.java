package com.learning.courses.service;

import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.CourseMapper;
import com.learning.courses.model.Course;
import com.learning.courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public Long createCourse(CreateCourseDTO createCourseDTO) {
        final Course course = courseMapper.toEntity(createCourseDTO);

        return courseRepository.save(course).getId();
    }

    @Transactional(readOnly = true)
    public CourseDTO getCourse(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
    }

    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO updatedCourse) {
        var course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Course.class.getSimpleName()));
        if (StringUtils.isNotBlank(updatedCourse.getCourseName())) {
            course.setCourseName(updatedCourse.getCourseName());
        }
        if (updatedCourse.getCourseLength() != null) {
            course.setCourseLength(updatedCourse.getCourseLength());
        }
        course = courseRepository.save(course);
        return courseMapper.toDTO(course);
    }
}
