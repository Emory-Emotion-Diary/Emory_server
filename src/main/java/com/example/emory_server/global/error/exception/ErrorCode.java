package com.example.emory_server.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "검증 되지 않은 토큰 입니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "일치 하는 RefreshToken이 존재 하지 않습니다."),
    INVALID_ROLE(401,"유효 하지 않은 역할입니다."),

    //user
    USER_NOT_FOUND(404, "해당 유저가 존재 하지 않습니다."),
    USER_MISMATCH(401, "유저가 일치 하지 않습니다."),
    PASSWORD_MISMATCH(401, "비밀 번호가 일치 하지 않습니다."),
    INVALID_USER(401, "유효 하지 않은 사용자입니다."),
    USER_EXIST(401, "유저가 이미 존재합니다."),

    //feign
    FEIGN_BAD_REQUEST(401, "Feign Bad Reqeust"),
    FEIGN_UNAUTHORIZED_EXCEPTION(402, "Feign Unauthorized Exception"),
    FEIGN_FORBIDDDEN_EXCEPTION(403, "Feign Forbidden Exception"),

    //s3
    IMAGE_NOT_FOUND(404, "이미지를 찾을 수 없음"),
    FAILED_UPLOAD(500, "업로드 실패"),
    FAILED_DELETE(500, "삭제 실패"),

    //emoji
    EMOJI_NOT_FOUND(404, "이모지 찾지 못함"),

    //diary
    DIARY_NOT_FOUND(404, "일기 찾지 못함"),

    // general
    BAD_REQUEST(400, "프론트 탓"),
    INTERNAL_SERVER_ERROR(500, "서버 탓");


    private final int statusCode;
    private final String ErrorMessage;
}
