package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.model.enums.PaperType;
import com.learning.courses.repository.PaperRepository;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

public class PaperControllerIT extends AbstractIntegrationTest {

    private static final Long TUTOR_ID = 77L;
    private static final Long STUDENT_ID = 78L;
    private static final Long PAPER_ID = 100L;

    @Autowired
    private PaperRepository paperRepository;

    @Test
    @Sql("/sql/createPaperInit.sql")
    void createPaper_whenValidTutor_thenReturnsPaperId() throws Exception {
        // given
        final HttpPost httpPost = new HttpPost("/api/papers");
        CreatePaperDTO createPaperDTO = CreatePaperDTO.builder()
                .type(PaperType.ARTICLE)
                .isbn("978-0-13-110362-7")
                .topic("Clean Code")
                .additionalAuthors("Martin Fowler")
                .tutorId(TUTOR_ID)
                .build();

        initRequestWithBody(createPaperDTO, httpPost);

        // when
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpPost);
            var paperId = retrieveResourceFromResponse(response, Long.class);

            // then
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(paperRepository.findById(paperId)).isPresent();
        }
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void createPaper_whenPersonIsStudent_thenReturnsBadRequest() throws Exception {
        // given
        final HttpPost httpPost = new HttpPost("/api/papers");
        CreatePaperDTO createPaperDTO = CreatePaperDTO.builder()
                .type(PaperType.THESIS)
                .topic("Some topic")
                .tutorId(STUDENT_ID)
                .build();

        initRequestWithBody(createPaperDTO, httpPost);

        // when/then
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpPost);
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void createPaper_whenTutorNotFound_thenReturnsNotFound() throws Exception {
        // TODO: implement
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void getPaper_whenPaperExists_thenReturnsPaperDTO() throws Exception {
        // given
        final HttpGet httpGet = new HttpGet("/api/papers/" + PAPER_ID);

        // when
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpGet);
            var paperDTO = retrieveResourceFromResponse(response, PaperDTO.class);

            // then
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(paperDTO.getId()).isEqualTo(PAPER_ID);
            assertThat(paperDTO.getTutorId()).isEqualTo(TUTOR_ID);
        }
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void getPaper_whenPaperNotFound_thenReturnsNotFound() throws Exception {
        // TODO: implement
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void getPapersByTutor_whenTutorHasPapers_thenReturnsListOfPapers() throws Exception {
        // given
        final HttpGet httpGet = new HttpGet("/api/papers/tutor/" + TUTOR_ID);

        // when
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpGet);
            var papers = retrieveResourceFromResponse(response, PaperDTO[].class);

            // then
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(papers).isNotEmpty();
            assertThat(papers).allMatch(p -> p.getTutorId().equals(TUTOR_ID));
        }
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void getPapersByTutor_whenTutorHasNoPapers_thenReturnsEmptyList() throws Exception {
        // TODO: implement
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void deletePaper_whenPaperExists_thenPaperIsRemoved() throws Exception {
        // given
        final HttpDelete httpDelete = new HttpDelete("/api/papers/" + PAPER_ID);

        // when
        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpDelete);

            // then
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
            assertThat(paperRepository.findById(PAPER_ID)).isEmpty();
        }
    }

    @Test
    @Sql("/sql/createPaperInit.sql")
    void deletePaper_whenPaperNotFound_thenReturnsNotFound() throws Exception {
        // TODO: implement
    }

}
