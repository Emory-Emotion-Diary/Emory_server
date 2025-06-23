package com.example.emory_server.global.security.jwt;

import com.example.emory_server.domain.auth.domain.RefreshToken;
import com.example.emory_server.domain.auth.domain.presentation.dto.response.TokenResponse;
import com.example.emory_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.emory_server.domain.auth.exception.ExpiredTokenException;
import com.example.emory_server.domain.auth.exception.InvalidTokenException;
import com.example.emory_server.domain.user.domain.repository.UserRepository;
import com.example.emory_server.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";
    private static final String CLAIM_TYPE = "type";
    private static final String CLAIM_USER_SECRET = "user";

    // Access Token 생성
    private String createAccessToken(String accountId) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(accountId)
                .claim(CLAIM_TYPE, ACCESS_TOKEN)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(String accountId) {
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim(CLAIM_TYPE, REFRESH_TOKEN)
                .setSubject(accountId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .token(refreshToken)
                        .timeToLive(jwtProperties.getRefreshExpiration())
                        .build()
        );

        return refreshToken;
    }

    // 토큰에서 Authentication 객체 반환
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(
                claims.getSubject() + ":" + claims.get(CLAIM_USER_SECRET)
        );

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Claims 추출
    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    // Access + Refresh Token 발급
    public TokenResponse receiveToken(String accountId) {
        Date now = new Date();

        return TokenResponse.builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .build();
    }

    // 헤더에서 JWT 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(jwtProperties.getPrefix().length() + 1);
        }

        return null;
    }
}
