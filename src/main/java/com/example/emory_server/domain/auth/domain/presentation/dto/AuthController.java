package com.example.emory_server.domain.auth.domain.presentation.dto;

import com.example.emory_server.domain.auth.domain.presentation.dto.request.LoginRequest;
import com.example.emory_server.domain.auth.domain.presentation.dto.response.TokenResponse;
import com.example.emory_server.domain.auth.service.LoginService;
import com.example.emory_server.domain.auth.service.ReissueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @PutMapping("/re-issue")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse reissueToken(@RequestHeader(name = "x-refresh-token") String token) {
        return reissueService.execute(token);
    }
}
