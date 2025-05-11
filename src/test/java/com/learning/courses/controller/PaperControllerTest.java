package com.learning.courses.controller;

import com.learning.courses.api.rest.controller.PaperController;
import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.service.PaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaperControllerTest {

    @Mock
    private PaperService paperService;

    @InjectMocks
    private PaperController paperController;

    private PaperDTO paperDTO;
    private CreatePaperDTO createPaperDTO;
    private List<PaperDTO> paperDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paperDTO = new PaperDTO(1L, "Test Paper", "Research", "Something", "123-456-789",
                "John Doe, Jane Smith", new Date(), 100, 1L);

        createPaperDTO = new CreatePaperDTO("Test Paper", "Research", "Something", "123-456-789",
                "John Doe, Jane Smith", new Date(), 100, 1L);

        paperDTOList = new ArrayList<>();
        paperDTOList.add(paperDTO);
    }

    @Test
    void shouldGetAllPapers() {
        when(paperService.getAllPapers()).thenReturn(paperDTOList);

        ResponseEntity<List<PaperDTO>> response = paperController.getAllPapers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paperDTOList, response.getBody());
        verify(paperService, times(1)).getAllPapers();
    }

    @Test
    void shouldCreatePaper() {
        when(paperService.createPaper(createPaperDTO)).thenReturn(paperDTO);

        ResponseEntity<PaperDTO> response = paperController.createPaper(createPaperDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(paperDTO, response.getBody());
        verify(paperService, times(1)).createPaper(createPaperDTO);
    }
}