package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.model.enums.CourseStatus;
import com.learning.courses.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @Operation(summary = "Get course")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

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

    @PatchMapping("/{id}/start")
    @Operation(summary = "Start course")
    public CourseStatus startCourse(@PathVariable Long id) {
        return courseService.startCourse(id);
    }

    @PatchMapping("/{id}/finish")
    @Operation(summary = "Finish course")
    public CourseStatus finishCourse(@PathVariable Long id) {
        return courseService.finishCourse(id);
    }
}
