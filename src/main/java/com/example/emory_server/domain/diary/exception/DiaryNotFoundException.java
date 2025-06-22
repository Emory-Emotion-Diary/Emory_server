package com.example.emory_server.domain.diary.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class DiaryNotFoundException extends EmoryException {
    public static final EmoryException EXCEPTION = new DiaryNotFoundException();

    public DiaryNotFoundException() {
        super(ErrorCode.DIARY_NOT_FOUND);
    }
}
