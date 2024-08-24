package com.example.geneweb.service.account;

import com.example.geneweb.entity.account.Account;
import com.example.geneweb.entity.account.Authority;
import com.example.geneweb.entity.account.AccountDetails;
import com.example.geneweb.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class JpaUserDetailsManger implements UserDetailsManager {
    private final AccountRepository accountRepository;

    public JpaUserDetailsManger(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        if (!userExists("admin")) {
            createUser(AccountDetails.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .name("admin")
                    .email("admin")
                    .authority(Authority.ROLE_ADMIN)
                    .mailAuth(true)
                    .build());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty())
            throw new UsernameNotFoundException(username);

        Account account = optionalAccount.get();

        AccountDetails accountDetails = AccountDetails.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .name(account.getName())
                .email(account.getEmail())
                .authority(account.getAuthority())
                .build();
        log.info("custom: {}",accountDetails.getUsername());

        return accountDetails;
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            log.error("이미 존재하는 아이디입니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (user instanceof AccountDetails accountDetails) {
            Account newAccount = Account.builder()
                    .username(accountDetails.getUsername())
                    .password(accountDetails.getPassword())
                    .name(accountDetails.getName())
                    .email(accountDetails.getEmail())
                    .authority(accountDetails.getAuthority())
                    .mailAuth(accountDetails.isMailAuth())
                    .build();
            log.info("authority: {}", accountDetails.getAuthorities());

            if (accountRepository.existsByEmail(accountDetails.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            accountRepository.save(newAccount);
        } else {
            throw new IllegalArgumentException("Unsupported UserDetails type");
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
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
}