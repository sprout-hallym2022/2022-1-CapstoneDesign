package com.example.geneweb.dto.account;

import com.example.geneweb.entity.AccountResearch;
import com.example.geneweb.entity.account.Account;
import com.example.geneweb.entity.account.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    @Setter
    private Authority authority;
    private List<Long> accountResearchId;
    private boolean mailAuth;

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .name(account.getName())
                .email(account.getEmail())
                .authority(account.getAuthority())
                .mailAuth(account.isMailAuth())
                .accountResearchId(account.getAccountResearches().stream().map(AccountResearch::getId).toList())
                .build();
    }
}
