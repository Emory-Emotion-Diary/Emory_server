package com.example.emory_server.domain.auth.exception;


import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class InvalidUserException extends EmoryException {

    public static final EmoryException EXCEPTION = new InvalidUserException();

    public InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }
}
