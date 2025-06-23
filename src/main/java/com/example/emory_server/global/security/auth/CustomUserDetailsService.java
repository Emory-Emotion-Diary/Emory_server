package com.example.emory_server.global.security.auth;

import com.example.emory_server.domain.user.domain.User;
import com.example.emory_server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없음"));

        return new CustomUserDetails(user);
    }

}
