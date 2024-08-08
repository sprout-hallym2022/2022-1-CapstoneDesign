package com.example.geneweb.dto.account;

import com.example.geneweb.entity.AccountResearch;
import com.example.geneweb.entity.account.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountResearchDto {
    private Long id;
    private AccountStatus status;
    private Long accountId;
    private Long researchId;

    public static AccountResearchDto fromEntity(AccountResearch accountResearch) {
        return AccountResearchDto.builder()
                .id(accountResearch.getId())
                .status(accountResearch.getStatus())
                .accountId(accountResearch.getAccount().getId())
                .researchId(accountResearch.getResearch().getId())
                .build();
    }
}
