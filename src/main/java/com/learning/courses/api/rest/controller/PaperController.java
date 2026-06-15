package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.service.PaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "PaperController", description = "Paper API")
@RequestMapping("/api/papers")
public class PaperController {

    private final PaperService paperService;

    @PostMapping
    @Operation(summary = "Create paper")
    public Long createPaper(@Valid @RequestBody CreatePaperDTO createPaperDTO) {
        return paperService.createPaper(createPaperDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update paper")
    public PaperDTO updatePaper(@PathVariable Long id, @RequestBody CreatePaperDTO updatePaperDTO) {
        return paperService.updatePaper(id, updatePaperDTO);
    }

    @GetMapping
    @Operation(summary = "Get all papers")
    public List<PaperDTO> getAllPapers() {
        return paperService.getAllPapers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get paper by id")
    public PaperDTO getPaper(@PathVariable Long id) {
        return paperService.getPaper(id);
    }

    @GetMapping("/tutor/{tutorId}")
    @Operation(summary = "Get all papers by tutor")
    public List<PaperDTO> getPapersByTutor(@PathVariable Long tutorId) {
        return paperService.getPapersByTutor(tutorId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete paper")
    public void deletePaper(@PathVariable Long id) {
        paperService.deletePaper(id);
    }
}
