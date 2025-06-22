package com.example.emory_server.domain.auth.domain.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Date accessExpiredAt;
    private Date refreshExpiredAt;
}
