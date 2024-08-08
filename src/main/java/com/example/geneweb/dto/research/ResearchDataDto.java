package com.example.geneweb.dto.research;

import com.example.geneweb.entity.research.ResearchData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResearchDataDto {
    private Long id;
    private String name;
    private String description;
    private Long researchId;

    public static ResearchDataDto fromEntity(ResearchData researchData) {
        return ResearchDataDto.builder()
                .id(researchData.getId())
                .name(researchData.getName())
                .description(researchData.getDescription())
                .researchId(researchData.getResearch().getId())
                .build();
    }
}
