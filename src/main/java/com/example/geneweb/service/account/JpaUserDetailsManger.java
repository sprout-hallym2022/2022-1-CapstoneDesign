package com.example.geneweb.service.account;

import com.example.geneweb.entity.account.Account;
import com.example.geneweb.entity.account.Authority;
import com.example.geneweb.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service // AccountService
public class JpaUserDetailsManger implements UserDetailsManager {
    private final AccountRepository accountRepository;

    public JpaUserDetailsManger(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        // 관리자 계정 생성
        if (!userExists("admin")) {
            createUser(Account.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .name("admin")
                    .authority(Authority.ROLE_ADMIN)
                    .mailAuth(true)
                    .build());
        }
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
