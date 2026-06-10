package com.learning.courses.repository;

import com.learning.courses.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findAllByTutorId(Long tutorId);
}
