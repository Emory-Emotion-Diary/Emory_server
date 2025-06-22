package com.example.emory_server.infrastructure.feign.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class FeignForbiddenException extends EmoryException {

    public static final EmoryException EXCEPTION = new FeignForbiddenException();

    private FeignForbiddenException(){
        super(ErrorCode.FEIGN_FORBIDDDEN_EXCEPTION);
    }
}
