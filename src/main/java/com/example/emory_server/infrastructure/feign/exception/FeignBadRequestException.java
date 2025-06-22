package com.example.emory_server.infrastructure.feign.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class FeignBadRequestException extends EmoryException {

    public static final EmoryException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException(){
        super(ErrorCode.FEIGN_BAD_REQUEST);
    }
}
