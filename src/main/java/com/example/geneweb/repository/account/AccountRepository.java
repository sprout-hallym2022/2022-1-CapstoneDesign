package com.example.geneweb.repository.account;

import com.example.geneweb.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
