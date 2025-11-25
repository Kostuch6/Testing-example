package com.learning.courses.mapper;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.model.Contact;

public interface ContactMapper {

    Contact toEntity(CreateContactDTO dto);

    ContactDTO toDTO(Contact contact);
}
