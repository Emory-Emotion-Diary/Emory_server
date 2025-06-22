package com.example.emory_server.domain.auth.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class PasswordMismatchException extends EmoryException {

    public static final EmoryException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
