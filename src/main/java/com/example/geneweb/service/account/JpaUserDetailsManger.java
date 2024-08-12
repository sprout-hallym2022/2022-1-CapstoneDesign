package com.example.geneweb.service.account;

import com.example.geneweb.entity.account.Account;
import com.example.geneweb.entity.account.Authority;
import com.example.geneweb.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (userExists(user.getUsername())) {
            log.error("이미 존재하는 아이디입니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (user instanceof Account account) {
            Account newAccount = Account.builder()
                    .username(account.getUsername())
                    .password(account.getPassword())
                    .name(account.getName())
                    .email(account.getEmail())
                    .authority(account.getAuthority())
                    .mailAuth(account.isMailAuth())
                    .build();
            log.info("authority: {}", account.getAuthorities());

            if (emailExists(newAccount.getEmail())) {
                log.error("이미 존재하는 이메일입니다.");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            accountRepository.save(newAccount);
        }
        else {
            throw new IllegalArgumentException("Unsupported UserDetails type");
        }
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
        return accountRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
