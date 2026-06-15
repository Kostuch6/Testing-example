package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ContactController", description = "Student contact API")
@RequestMapping("/api/persons/{personId}/contacts")
class ContactController {

  private final ContactService contactService;

  @PostMapping
  @Operation(summary = "Create contact for student")
  public Long createContact(@PathVariable Long personId, @Valid @RequestBody CreateContactDTO createContactDTO) {
    return contactService.createContact(personId, createContactDTO);
  }

  @GetMapping
  @Operation(summary = "Get all contacts for student")
  public List<ContactDTO> getContacts(@PathVariable Long personId) {
    return contactService.getContacts(personId);
  }

  @GetMapping("/{contactId}")
  @Operation(summary = "Get contact for student")
  public ContactDTO getContact(@PathVariable Long personId, @PathVariable Long contactId) {
    return contactService.getContact(personId, contactId);
  }

  @PutMapping("/{contactId}")
  @Operation(summary = "Update contact for student")
  public ContactDTO updateContact(@PathVariable Long personId, @PathVariable Long contactId,
      @Valid @RequestBody CreateContactDTO updatedContact) {
    return contactService.updateContact(personId, contactId, updatedContact);
  }

  @DeleteMapping("/{contactId}")
  @Operation(summary = "Delete contact for student")
  public void deleteContact(@PathVariable Long personId, @PathVariable Long contactId) {
    contactService.deleteContact(personId, contactId);
  }
}
