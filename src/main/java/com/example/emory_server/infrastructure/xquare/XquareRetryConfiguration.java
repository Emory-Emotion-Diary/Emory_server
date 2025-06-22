package com.example.emory_server.infrastructure.xquare;


import com.example.emory_server.infrastructure.feign.Custom5xxRetryer;
import com.example.emory_server.infrastructure.feign.CustomErrorDecoder;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class XquareRetryConfiguration {

    @Bean
    public Retryer retryer() {
        return new Custom5xxRetryer();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
