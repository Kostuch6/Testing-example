package com.learning.courses.service;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.PaperMapper;
import com.learning.courses.model.Paper;
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
            throw null;
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
        personService.getPersonEntity(tutorId);
        return paperMapper.toDTO(paperRepository.findAllByTutorId(tutorId));
    }

    @Transactional(readOnly = true)
    public List<PaperDTO> getAllPapers() {
        return paperMapper.toDTO(paperRepository.findAll());
    }

    @Transactional
    public void deletePaper(Long id) {
        if (!paperRepository.existsById(id)) {
            throw new EntityNotFoundException(id, Paper.class.getSimpleName());
        }
        paperRepository.deleteById(id);
    }
}
