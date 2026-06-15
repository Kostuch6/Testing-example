package com.learning.courses.mapper;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContactMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "person", ignore = true)
  Contact toEntity(CreateContactDTO createContactDTO);

  @Mapping(target = "personId", source = "person.id")
  ContactDTO toDTO(Contact contact);

  List<ContactDTO> toDTO(List<Contact> contacts);
}
