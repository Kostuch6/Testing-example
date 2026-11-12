package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.model.enums.Degree;
import com.learning.courses.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "CourseController", description = "Course API")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Operation(summary = "Create course")
    public Long createCourse(@RequestBody CreateCourseDTO createCourseDTO) {
        return courseService.createCourse(createCourseDTO);
    }

//    @GetMapping
//    @Operation(summary = "Get course")
//    public CourseDTO getAllCourses(@RequestParam String courseStatus) {
//        return courseService.getAllCourses(courseStatus);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course")
    public CourseDTO getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping("/{id}/persons")
    @Operation(summary = "Get course")
    public CourseDTO getPersonsFromCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update course")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    public void finishCourse(Long studentId, Degree degree) {
        
    }
}
