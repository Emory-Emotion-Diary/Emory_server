package com.example.emory_server.global.config;

import com.example.emory_server.global.error.GlobalExceptionFilter;
import com.example.emory_server.global.security.jwt.JwtTokenFilter;
import com.example.emory_server.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity httpSecurity) {

        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(globalExceptionFilter, JwtTokenFilter.class);

    }
}
