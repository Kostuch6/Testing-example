package com.learning.courses.controller;

import com.learning.courses.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

public class ContactControllerIT extends AbstractIntegrationTest {

  private static final Long STUDENT_ID = 1L;
  private static final Long TUTOR_ID = 77L;

  @Test
  void createContact_shouldReturnCreatedContactId() {
    // TODO: verify that POST /api/persons/{personId}/contacts returns contact id for a student
  }

  @Test
  void createContact_shouldRejectContactForTutor() {
    // TODO: verify that creating contact for a tutor returns 400 with InvalidRoleException
  }

  @Test
  void getContacts_shouldReturnAllContactsForStudent() {
    // TODO: verify that GET /api/persons/{personId}/contacts returns all contacts belonging to the student
  }

  @Test
  void getContact_shouldReturnSingleContact() {
    // TODO: verify that GET /api/persons/{personId}/contacts/{contactId} returns the requested contact
  }

  @Test
  void getContact_shouldReturnNotFoundWhenContactDoesNotBelongToStudent() {
    // TODO: verify that requesting a contact assigned to another person returns 404
  }

  @Test
  void updateContact_shouldModifyExistingContact() {
    // TODO: verify that PUT /api/persons/{personId}/contacts/{contactId} updates type, value and label
  }

  @Test
  void deleteContact_shouldRemoveContact() {
    // TODO: verify that DELETE /api/persons/{personId}/contacts/{contactId} removes the contact from database
  }

  @Test
  void createContact_shouldAllowMultipleContactsOfSameType() {
    // TODO: verify that a student can have multiple emails, phone numbers or addresses
  }
}
