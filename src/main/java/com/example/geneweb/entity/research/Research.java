package com.example.geneweb.entity.research;

import com.example.geneweb.entity.AccountResearch;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;   // 연구 제목
    @Setter
    private String description; // 연구 설명
    @Setter
    private ResearchStatus status;  // 연구 상황

    @Setter
    @OneToMany(mappedBy = "research")
    private List<AccountResearch> accountResearches;

    @Setter
    @OneToMany(mappedBy = "research")
    private List<ResearchData> researchData;
}
