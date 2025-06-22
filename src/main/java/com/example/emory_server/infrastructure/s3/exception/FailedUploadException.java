package com.example.emory_server.infrastructure.s3.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class FailedUploadException extends EmoryException {
    public static final EmoryException EXCEPTION = new FailedUploadException();

    private FailedUploadException() {
        super(ErrorCode.FAILED_UPLOAD);
    }
}
