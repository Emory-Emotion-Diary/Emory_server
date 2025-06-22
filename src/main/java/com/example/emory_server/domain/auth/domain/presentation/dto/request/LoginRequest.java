package com.example.emory_server.domain.auth.domain.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotNull
    private String accountId;

    @NotNull
    private String password;
}
