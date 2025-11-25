package com.learning.courses.dto;

import com.learning.courses.model.enums.ContactType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CreateContactDTO implements Serializable {

    private Long personId;
    private ContactType type;
    private String value;
    private String description;
}
