package com.example.emory_server.infrastructure.feign.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class FeignUnauthorizedException extends EmoryException {

    public static final EmoryException EXCEPTION = new FeignUnauthorizedException();

    private FeignUnauthorizedException(){
        super(ErrorCode.FEIGN_UNAUTHORIZED_EXCEPTION);
    }
}
