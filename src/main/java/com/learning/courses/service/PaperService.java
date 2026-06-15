package com.learning.courses.service;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.exception.InvalidRoleException;
import com.learning.courses.mapper.PaperMapper;
import com.learning.courses.model.Paper;
import com.learning.courses.model.Person;
import com.learning.courses.model.enums.Role;
import com.learning.courses.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final PaperMapper paperMapper;
    private final PersonService personService;

    @Transactional
    public Long createPaper(CreatePaperDTO createPaperDTO) {
        var tutor = personService.getPersonEntity(createPaperDTO.getTutorId());
        if (tutor.getRole() != Role.TUTOR) {
            throw new InvalidRoleException(createPaperDTO.getTutorId(), Role.TUTOR, tutor.getRole());
        }
        Paper paper = paperMapper.toEntity(createPaperDTO);
        return paperRepository.save(paper).getId();
    }

    @Transactional(readOnly = true)
    public PaperDTO getPaper(Long id) {
        return paperRepository.findById(id)
                .map(paperMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(id, Paper.class.getSimpleName()));
    }

    @Transactional(readOnly = true)
    public List<PaperDTO> getPapersByTutor(Long tutorId) {
        Person person = personService.getPersonEntity(tutorId);
        if (person.getRole() != Role.TUTOR) {
            throw new InvalidRoleException(tutorId, Role.TUTOR, person.getRole());
        }
        return paperMapper.toDTO(paperRepository.findAllByTutorId(tutorId));
    }

    @Transactional(readOnly = true)
    public List<PaperDTO> getAllPapers() {
        return paperMapper.toDTO(paperRepository.findAll());
    }

    @Transactional
    public PaperDTO updatePaper(Long id, CreatePaperDTO updatePaperDTO) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Paper.class.getSimpleName()));
        if (updatePaperDTO.getTutorId() != null) {
            var tutor = personService.getPersonEntity(updatePaperDTO.getTutorId());
            if (tutor.getRole() != Role.TUTOR) {
                throw new InvalidRoleException(updatePaperDTO.getTutorId(), Role.TUTOR, tutor.getRole());
            }
            paper.setTutor(tutor);
        }
        if (updatePaperDTO.getTitle() != null) paper.setTitle(updatePaperDTO.getTitle());
        if (updatePaperDTO.getType() != null) paper.setType(updatePaperDTO.getType());
        if (updatePaperDTO.getTopic() != null) paper.setTopic(updatePaperDTO.getTopic());
        if (updatePaperDTO.getIsbn() != null) paper.setIsbn(updatePaperDTO.getIsbn());
        if (updatePaperDTO.getAdditionalAuthors() != null) paper.setAdditionalAuthors(updatePaperDTO.getAdditionalAuthors());
        if (updatePaperDTO.getPublicationYear() != null) paper.setPublicationYear(updatePaperDTO.getPublicationYear());
        return paperMapper.toDTO(paperRepository.save(paper));
    }

    @Transactional
    public void deletePaper(Long id) {
        if (!paperRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Paper.class.getSimpleName());
        }
        paperRepository.deleteById(id);
    }
}
