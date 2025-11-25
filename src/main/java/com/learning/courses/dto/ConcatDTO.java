package com.learning.courses.dto;

import com.learning.courses.model.enums.ContactType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class ContactDTO extends CreateContactDTO implements Serializable {

    private Long id;


}
