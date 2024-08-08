package com.example.geneweb.entity.research;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String description;

    @Setter
    @ManyToOne
    @JoinColumn(name = "research_id")
    private Research research;

}
