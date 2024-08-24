package com.example.geneweb.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private boolean mailAuth;
    private Authority authority;
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authority.getAuthority() != null) {
            String role = authority.getAuthority();
            return Collections.singleton(new SimpleGrantedAuthority(role));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
