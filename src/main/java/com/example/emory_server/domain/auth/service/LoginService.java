package com.example.emory_server.domain.auth.service;

import com.example.emory_server.domain.auth.domain.presentation.dto.request.LoginRequest;
import com.example.emory_server.domain.auth.domain.presentation.dto.response.TokenResponse;
import com.example.emory_server.domain.auth.exception.PasswordMismatchException;
import com.example.emory_server.domain.user.domain.User;
import com.example.emory_server.domain.user.domain.repository.UserRepository;
import com.example.emory_server.domain.user.exception.UserNotFoundException;
import com.example.emory_server.global.security.jwt.JwtTokenProvider;
import com.example.emory_server.infrastructure.xquare.XquareClient;
import com.example.emory_server.infrastructure.xquare.dto.XquareUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final XquareClient xquareClient;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse login(LoginRequest request) {
        boolean exists = userRepository.existsByAccountId(request.getAccountId());

        return exists ? loginExistingUser(request) : registerAndLoginNewUser(request);
    }

    private TokenResponse loginExistingUser(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(user.getAccountId());
    }

    private TokenResponse registerAndLoginNewUser(LoginRequest request) {
        XquareUserResponse xquareUser = xquareClient.xquareUser(request);

        User newUser = new User(
                xquareUser.getAccountId(),
                xquareUser.getPassword(),
                xquareUser.getName(),
                xquareUser.getGrade(),
                xquareUser.getClassNum(),
                xquareUser.getNum()
        );

        userRepository.save(newUser);

        return jwtTokenProvider.receiveToken(xquareUser.getAccountId());
    }
}
