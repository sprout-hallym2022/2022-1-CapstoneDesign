package com.example.geneweb.dto.research;

import com.example.geneweb.entity.AccountResearch;
import com.example.geneweb.entity.research.Research;
import com.example.geneweb.entity.research.ResearchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResearchDto {
    private Long id;
    private String title;
    private String description;
    private ResearchStatus status;
    private List<Long> accountResearchId;
    private List<Long> researchDataId;

    public static ResearchDto fromEntity(Research research) {
        ResearchDtoBuilder builder = ResearchDto.builder()
                .id(research.getId())
                .title(research.getTitle())
                .description(research.getDescription());

        if (research.getAccountResearches() != null) {
            builder.accountResearchId(research.getAccountResearches().stream().map(AccountResearch::getId).toList());
        } else {
            builder.accountResearchId(Collections.emptyList());
        }
        return builder().build();
    }



}
