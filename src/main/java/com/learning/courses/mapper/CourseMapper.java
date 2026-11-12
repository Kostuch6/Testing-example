package com.learning.courses.mapper;

import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CourseMapper {

    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "tutor.id", source = "tutorId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedPersonCourse", ignore = true)
    Course toEntity(CreateCourseDTO createCourseDTO);

    @Mapping(target = "tutorId", source = "tutor.id")
    CourseDTO toDTO(Course course);

    List<CourseDTO> toDTO(List<Course> courseList);

}
