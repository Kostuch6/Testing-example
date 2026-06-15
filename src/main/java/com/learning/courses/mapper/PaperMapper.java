package com.learning.courses.mapper;

import com.learning.courses.dto.CreatePaperDTO;
import com.learning.courses.dto.PaperDTO;
import com.learning.courses.model.Paper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaperMapper {

    @Mapping(target = "tutor.id", source = "tutorId")
    @Mapping(target = "id", ignore = true)
    Paper toEntity(CreatePaperDTO createPaperDTO);

    @Mapping(target = "tutorId", source = "tutor.id")
    PaperDTO toDTO(Paper paper);

    List<PaperDTO> toDTO(List<Paper> papers);
}
