package com.learning.courses;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.exception.EntityNotFoundException;
import com.learning.courses.mapper.PaperMapper;
import com.learning.courses.model.Paper;
import com.learning.courses.model.Person;
import com.learning.courses.repository.PaperRepository;
import com.learning.courses.repository.PersonRepository;
import com.learning.courses.service.PaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaperServiceTest {

    @Mock
    private PaperRepository paperRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PaperMapper paperMapper;

    @InjectMocks
    private PaperService paperService;

    private Paper paper;
    private PaperDTO paperDTO;
    private CreatePaperDTO createPaperDTO;
    private Person tutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Date publicationDate = new Date();

        tutor = new Person();
        tutor.setId(1L);

        paper = new Paper();
        paper.setId(1L);
        paper.setTitle("Test Paper");
        paper.setPaperType("Research");
        paper.setTopic("Something");
        paper.setIsbn("123-456-789");
        paper.setAdditionalAuthors("John Doe, Jane Smith");
        paper.setPublicationDate(publicationDate);
        paper.setNumberOfPages(100);
        paper.setTutor(tutor);

        paperDTO = new PaperDTO(1L, "Test Paper", "Research", "Something", "123-456-789",
                "John Doe, Jane Smith", publicationDate, 100, 1L);

        createPaperDTO = new CreatePaperDTO("Test Paper", "Research", "Something", "123-456-789",
                "John Doe, Jane Smith", publicationDate, 100, 1L);
    }

    @Test
    void shouldGetAllPapers() {
        List<Paper> papers = new ArrayList<>();
        papers.add(paper);

        when(paperRepository.findAll()).thenReturn(papers);
        when(paperMapper.toDTO(any(Paper.class))).thenReturn(paperDTO);

        List<PaperDTO> result = paperService.getAllPapers();

        assertEquals(1, result.size());
        assertEquals(paperDTO, result.get(0));
        verify(paperRepository, times(1)).findAll();
    }

    @Test
    void shouldGetPaperById() {
        when(paperRepository.findById(1L)).thenReturn(Optional.of(paper));
        when(paperMapper.toDTO(paper)).thenReturn(paperDTO);

        PaperDTO result = paperService.getPaperById(1L);

        assertEquals(paperDTO, result);
        verify(paperRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreatePaper() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(tutor));
        when(paperMapper.toEntity(createPaperDTO)).thenReturn(paper);
        when(paperRepository.save(paper)).thenReturn(paper);
        when(paperMapper.toDTO(paper)).thenReturn(paperDTO);

        PaperDTO result = paperService.createPaper(createPaperDTO);

        assertEquals(paperDTO, result);
        verify(personRepository, times(1)).findById(1L);
        verify(paperRepository, times(1)).save(paper);
    }

    @Test
    void shouldThrowExceptionWhenPaperNotFound() {
        when(paperRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            paperService.getPaperById(99L);
        });
    }
}