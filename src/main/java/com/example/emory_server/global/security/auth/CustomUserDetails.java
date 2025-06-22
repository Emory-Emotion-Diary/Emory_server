package com.example.emory_server.global.security.auth;

import com.example.emory_server.domain.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public record CustomUserDetails(User user) implements UserDetails {

    @Override
    public String getUsername() {   //로그인 아이디 역할을 하는 값 반환
        return user.getAccountId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //사용자의 권한 목록을 반환
        return new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_")));
    }

    @Override
    public String getPassword() {
        return user.getAccountId();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
