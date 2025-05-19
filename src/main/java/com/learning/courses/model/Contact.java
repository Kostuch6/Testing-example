package com.learning.courses.model;

import com.learning.courses.model.enums.ContactType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    private String value;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}

