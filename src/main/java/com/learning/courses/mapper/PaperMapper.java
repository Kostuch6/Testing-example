package com.learning.courses.mapper;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.model.Paper;
import org.springframework.stereotype.Component;

@Component
public class PaperMapper {

    public PaperDTO toDTO(Paper paper) {
        return new PaperDTO(
                paper.getId(),
                paper.getTitle(),
                paper.getPaperType(),
                paper.getTopic(),
                paper.getIsbn(),
                paper.getAdditionalAuthors(),
                paper.getPublicationDate(),
                paper.getNumberOfPages(),
                null
        );
    }

    public Paper toEntity(CreatePaperDTO createPaperDTO) {
        return new Paper(
                createPaperDTO.getTitle(),
                createPaperDTO.getPaperType(),
                createPaperDTO.getTopic(),
                createPaperDTO.getIsbn(),
                createPaperDTO.getAdditionalAuthors(),
                createPaperDTO.getPublicationDate(),
                createPaperDTO.getNumberOfPages()
        );
    }
}