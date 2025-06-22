package com.example.emory_server.infrastructure.xquare.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class XquareUserResponse {
    private UUID id;
    private String accountId;
    private String password;
    private String name;
    private Integer grade;
    private Integer classNum;
    private Integer num;
    private LocalDate birth_day;
    private String profileImgUrl;
}
