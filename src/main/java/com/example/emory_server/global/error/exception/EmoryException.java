package com.example.emory_server.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmoryException extends RuntimeException {
    private final ErrorCode errorCode;
}
