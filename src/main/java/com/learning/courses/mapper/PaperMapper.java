package com.learning.courses.mapper;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.model.Paper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface PaperMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tutor", ignore = true) // ustawiamy ręcznie w serwisie
    Paper toEntity(CreatePaperDTO dto);

    @Mapping(source = "tutor.id", target = "tutorId")
    @Mapping(expression = "java(paper.getTutor().getFirstName() + \" \" + paper.getTutor().getLastName())",
            target = "tutorName")
    PaperDTO toDTO(Paper paper);

    List<PaperDTO> toDTO(List<Paper> papers);
}

