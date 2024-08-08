package com.example.geneweb.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MailAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authString;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private LocalDateTime sendTime;
}
