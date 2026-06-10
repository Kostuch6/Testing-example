package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import com.learning.courses.repository.PaperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
  void deletePaper_withExistingId_deletesPaper() {
  }

  @Test
  void deletePaper_withNonExistentId_returnsNotFoundError() {
  }
}
