package com.learning.courses.Service;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PaperService {
    protected void CreatePaperWithoutTutor(){}

    protected void CreateCourseWithNoExistingTutorId(){}

    protected void CreatePaperWithStudentIdasTutorId(){}
    protected void CreatePaperWithoutTopic(){}

}
