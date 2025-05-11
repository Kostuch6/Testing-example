package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping
    public ResponseEntity<List<PaperDTO>> getAllPapers() {
        return ResponseEntity.ok(paperService.getAllPapers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaperDTO> getPaperById(@PathVariable Long id) {
        return ResponseEntity.ok(paperService.getPaperById(id));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<PaperDTO>> getPapersByTutorId(@PathVariable Long tutorId) {
        return ResponseEntity.ok(paperService.getPapersByTutorId(tutorId));
    }

    @PostMapping
    public ResponseEntity<PaperDTO> createPaper(@RequestBody CreatePaperDTO createPaperDTO) {
        return new ResponseEntity<>(paperService.createPaper(createPaperDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaperDTO> updatePaper(@PathVariable Long id, @RequestBody CreatePaperDTO updatePaperDTO) {
        return ResponseEntity.ok(paperService.updatePaper(id, updatePaperDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaper(@PathVariable Long id) {
        paperService.deletePaper(id);
        return ResponseEntity.noContent().build();
    }
}