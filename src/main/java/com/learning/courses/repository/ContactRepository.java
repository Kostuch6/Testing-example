package com.learning.courses.repository;

import com.learning.courses.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

  List<Contact> findAllByPersonId(Long personId);
}
