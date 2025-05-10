package com.learning.courses.service;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.PaperMapper;
import com.learning.courses.model.Paper;
import com.learning.courses.model.Person;
import com.learning.courses.repository.PaperRepository;
import com.learning.courses.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final PersonRepository personRepository;
    private final PaperMapper paperMapper;

    public List<PaperDTO> getAllPapers() {
        return paperRepository.findAll().stream()
                .map(paperMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PaperDTO> getPapersByTutorId(Long tutorId) {
        return paperRepository.findByTutorId(tutorId).stream()
                .map(paperMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaperDTO getPaperById(Long id) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Paper"));
        return paperMapper.toDTO(paper);
    }

    public PaperDTO createPaper(CreatePaperDTO createPaperDTO) {
        Person tutor = personRepository.findById(createPaperDTO.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException(createPaperDTO.getTutorId(), "Person"));

        Paper paper = paperMapper.toEntity(createPaperDTO);

        Paper savedPaper = paperRepository.save(paper);
        return paperMapper.toDTO(savedPaper);
    }

    public PaperDTO updatePaper(Long id, CreatePaperDTO updatePaperDTO) {
        Paper existingPaper = paperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Paper"));

        existingPaper.setTitle(updatePaperDTO.getTitle());
        existingPaper.setPaperType(updatePaperDTO.getPaperType());
        existingPaper.setTopic(updatePaperDTO.getTopic());
        existingPaper.setIsbn(updatePaperDTO.getIsbn());
        existingPaper.setAdditionalAuthors(updatePaperDTO.getAdditionalAuthors());
        existingPaper.setPublicationDate(updatePaperDTO.getPublicationDate());
        existingPaper.setNumberOfPages(updatePaperDTO.getNumberOfPages());

        Paper updatedPaper = paperRepository.save(existingPaper);
        return paperMapper.toDTO(updatedPaper);
    }

    public void deletePaper(Long id) {
        paperRepository.deleteById(id);
    }
}