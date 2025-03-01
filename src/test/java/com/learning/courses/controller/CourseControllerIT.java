package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.model.Course;
import com.learning.courses.model.Person;
import com.learning.courses.repository.CourseRepository;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


public class CourseControllerIT extends AbstractIntegrationTest {

    private static final Long TUTOR_ID = 77L;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Sql("/sql/createCourseInit.sql")
    void createCourse() throws Exception {
        //given
        final HttpPost httpPost = new HttpPost("/api/courses");
        CreateCourseDTO courseDTO = CreateCourseDTO.builder()
                .courseLength(10)
                .courseName("Mathematics")
                .tutorId(TUTOR_ID)
                .build();

        initRequestWithBody(courseDTO, httpPost);

        //when
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpPost);

            var courseId = retrieveResourceFromResponse(response, Long.class);

            //then
            var courseOpt = courseRepository.findById(courseId);

            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(courseOpt).isPresent()
                    .map(Course::getTutor)
                    .map(Person::getId).get()
                    .isEqualTo(TUTOR_ID);
        }
    }
}
