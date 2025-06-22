package com.example.emory_server;

import com.example.emory_server.global.security.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(JwtProperties.class)
@EnableFeignClients
@ConfigurationPropertiesScan
public class EmoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoryServerApplication.class, args);
    }

}
