package com.example.emory_server.domain.auth.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class ExpiredTokenException extends EmoryException {

    public static final EmoryException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
