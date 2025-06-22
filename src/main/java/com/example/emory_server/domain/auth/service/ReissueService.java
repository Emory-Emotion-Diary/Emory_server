package com.example.emory_server.domain.auth.service;

import com.example.emory_server.domain.auth.domain.RefreshToken;
import com.example.emory_server.domain.auth.domain.presentation.dto.response.TokenResponse;
import com.example.emory_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.emory_server.domain.auth.exception.InvalidTokenException;
import com.example.emory_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final RefreshTokenRepository refreshTokenRepository;;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.getAccountId());
    }
}
