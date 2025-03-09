package com.learning.courses.service;

import com.learning.courses.dto.CreatePersonDTO;
import com.learning.courses.dto.PersonDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.PersonMapper;
import com.learning.courses.model.Person;
import com.learning.courses.repository.PersonRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @Transactional
  public Long createPerson(CreatePersonDTO createPersonDTO) {
    final Person person = personMapper.toEntity(createPersonDTO);

    return personRepository.save(person).getId();
  }

  @Transactional(readOnly = true)
  public PersonDTO getPerson(@NotNull @Positive Long id) {
    return personRepository.findById(id)
        .map(personMapper::toDTO)
        .orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
  }

  @Transactional(readOnly = true)
  public Person getPersonEntity(@NotNull @Positive Long id) {
    return personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
  }

  @Transactional
  public PersonDTO updatePerson(Long id, PersonDTO updatedPerson) {
    var person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
    person.setRole(updatedPerson.getRole());
    person.setFirstName(updatedPerson.getFirstName());
    person.setLastName(updatedPerson.getLastName());
    person.setIdentityNumber(updatedPerson.getIdentityNumber());
    person = personRepository.save(person);
    return personMapper.toDTO(person);
  }

}
