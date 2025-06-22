package com.example.emory_server.global.config;

import com.example.emory_server.global.error.CustomAccessDeniedHandler;
import com.example.emory_server.global.error.CustomAuthenticationEntryPoint;
import com.example.emory_server.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        HttpSecurity with = httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource())
                )



                .headers(headers -> {
                            headers
                                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                    );
                        }
                )


                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                        .accessDeniedHandler((request, response, accessDeniedException) -> response
                                .sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied"))
                )




                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers( "/user/**","/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                .with(new FilterConfig(jwtTokenProvider, objectMapper), Customizer.withDefaults());




        return httpSecurity.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*")); // 모든 도메인 허용
        configuration.setAllowedMethods(Arrays.asList( "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowCredentials(false);
        configuration.addAllowedHeader("*"); // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위에서 설정한 CORS 설정 적용
        return source;
    }
}
