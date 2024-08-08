package com.example.geneweb.entity;

import com.example.geneweb.entity.account.Account;
import com.example.geneweb.entity.account.AccountStatus;
import com.example.geneweb.entity.research.Research;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Setter
    @ManyToOne
    @JoinColumn(name = "research_id")
    private Research research;
}
