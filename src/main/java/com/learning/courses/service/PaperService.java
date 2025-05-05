package com.learning.courses.service;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.mapper.PaperMapper;
import com.learning.courses.model.Paper;
import com.learning.courses.model.Person;
import com.learning.courses.repository.PaperRepository;
import com.learning.courses.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaperService {

    private final PaperRepository paperRepo;
    private final PersonRepository personRepo;
    private final PaperMapper paperMapper;

    public PaperService(PaperRepository paperRepo,
                        PersonRepository personRepo,
                        PaperMapper paperMapper) {
        this.paperRepo = paperRepo;
        this.personRepo = personRepo;
        this.paperMapper = paperMapper;
    }

    @Transactional
    public PaperDTO createPaper(CreatePaperDTO dto) {
        // 1) zamapuj podstawowe pola
        Paper paper = paperMapper.toEntity(dto);

        // 2) znajdź tutora po ID i zweryfikuj rolę
        Person tutor = personRepo.findById(dto.getTutorId())
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found: " + dto.getTutorId()));
        if (tutor.getRole() != com.learning.courses.model.enums.Role.TUTOR) {
            throw new IllegalArgumentException("Person is not a tutor: " + dto.getTutorId());
        }

        // 3) ustaw powiązanie
        paper.setTutor(tutor);

        // 4) zapisz i zamapuj na DTO
        Paper saved = paperRepo.save(paper);
        return paperMapper.toDTO(saved);
    }
}
