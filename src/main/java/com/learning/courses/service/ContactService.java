package com.learning.courses.service;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.exception.InvalidRoleException;
import com.learning.courses.mapper.ContactMapper;
import com.learning.courses.model.Contact;
import com.learning.courses.model.enums.Role;
import com.learning.courses.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

  private final ContactRepository contactRepository;
  private final ContactMapper contactMapper;
  private final PersonService personService;

  @Transactional
  public Long createContact(Long personId, CreateContactDTO createContactDTO) {
    var person = personService.getPersonEntity(personId);
    if (person.getRole() != Role.STUDENT) {
      throw new InvalidRoleException(personId, Role.STUDENT, person.getRole());
    }

    final Contact contact = contactMapper.toEntity(createContactDTO);
    contact.setPerson(person);
    return contactRepository.save(contact).getId();
  }

  @Transactional(readOnly = true)
  public ContactDTO getContact(Long personId, Long contactId) {
    return contactRepository.findById(contactId)
        .filter(contact -> contact.getPerson().getId().equals(personId))
        .map(contactMapper::toDTO)
        .orElseThrow(() -> new EntityNotFoundException(contactId, Contact.class.getSimpleName()));
  }

  @Transactional(readOnly = true)
  public List<ContactDTO> getContacts(Long personId) {
    personService.getPersonEntity(personId);
    return contactRepository.findAllByPersonId(personId).stream()
        .map(contactMapper::toDTO)
        .toList();
  }

  @Transactional
  public ContactDTO updateContact(Long personId, Long contactId, CreateContactDTO updatedContact) {
    var contact = contactRepository.findById(contactId)
        .filter(c -> c.getPerson().getId().equals(personId))
        .orElseThrow(() -> new EntityNotFoundException(contactId, Contact.class.getSimpleName()));

    if (updatedContact.getType() != null) {
      contact.setType(updatedContact.getType());
    }
    if (StringUtils.isNotBlank(updatedContact.getValue())) {
      contact.setValue(updatedContact.getValue());
    }
    if (updatedContact.getLabel() != null) {
      contact.setLabel(updatedContact.getLabel());
    }

    return contactMapper.toDTO(contactRepository.save(contact));
  }

  @Transactional
  public void deleteContact(Long personId, Long contactId) {
    var contact = contactRepository.findById(contactId)
        .filter(c -> c.getPerson().getId().equals(personId))
        .orElseThrow(() -> new EntityNotFoundException(contactId, Contact.class.getSimpleName()));

    contactRepository.delete(contact);
  }
}
