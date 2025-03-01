package com.learning.courses.service;

import com.learning.courses.model.enums.PersonCourseStatus;
import com.learning.courses.model.enums.Degree;
import com.learning.courses.model.enums.Role;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

  public List<Degree> getDegreeList() {
    return Arrays.stream(Degree.values()).toList();
  }

  public List<Role> getRoles() {
    return Arrays.stream(Role.values()).toList();
  }

  public List<PersonCourseStatus> getCourseStatuses() {
    return Arrays.stream(PersonCourseStatus.values()).toList();
  }

}
