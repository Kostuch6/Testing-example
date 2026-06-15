package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.repository.PaperRepository;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

public class PaperControllerIT extends AbstractIntegrationTest {

  @Autowired
  private PaperRepository paperRepository;

  @Test
  void createPaper_withValidTutor_returnsPaperId() {
  }

  @Test
  void createPaper_withStudentRole_returnsInvalidRoleError() {
  }

  @Test
  void createPaper_withNonExistentTutor_returnsNotFoundError() {
  }

  @Test
  void getPaper_withExistingId_returnsPaper() {
  }

  @Test
  void getPaper_withNonExistentId_returnsNotFoundError() {
  }

  @Test
  void getPapersByTutor_returnsAllPapersForTutor() {
  }

  @Test
  void getPapersByTutor_withNonExistentTutor_returnsNotFoundError() {
  }

  @Test
  void getAllPapers_returnsAllPapers() {
  }

  @Test
  @Sql("/sql/createPaperInit.sql")
  void updatePaper_withValidData_returnsUpdatedPaper() {
  }

  @Test
  void updatePaper_withNonExistentId_returnsNotFoundError() throws Exception {
  }

  @Test
  void deletePaper_withExistingId_deletesPaper() {
  }

  @Test
  void deletePaper_withNonExistentId_returnsNotFoundError() {
  }
}
