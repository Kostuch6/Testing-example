package com.learning.courses.api.rest.controller;

import com.learning.courses.model.enums.CourseStatus;
import com.learning.courses.model.enums.Degree;
import com.learning.courses.model.enums.Role;
import com.learning.courses.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "DictionaryController", description = "Dictionaries API")
@RequestMapping("/api/dictionaries")
class DictionaryController {

  private final DictionaryService dictionaryService;

  @GetMapping("/degrees")
  @Operation(summary = "Get all degrees")
  public List<Degree> getDegrees() {
    return dictionaryService.getDegreeList();
  }

  @GetMapping("/roles")
  @Operation(summary = "Get all roles")
  public List<Role> getRoles() {
    return dictionaryService.getRoles();
  }

  @GetMapping("/statuses")
  @Operation(summary = "Get all course statuses")
  public List<CourseStatus> getCourseStatuses() {
    return dictionaryService.getCourseStatuses();
  }
}
