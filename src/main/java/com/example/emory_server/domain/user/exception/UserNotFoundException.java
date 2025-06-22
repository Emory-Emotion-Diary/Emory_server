package com.example.emory_server.domain.user.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class UserNotFoundException extends EmoryException {
    public static final EmoryException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
