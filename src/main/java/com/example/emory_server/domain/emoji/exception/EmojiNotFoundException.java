package com.example.emory_server.domain.emoji.exception;

import com.example.emory_server.global.error.exception.EmoryException;
import com.example.emory_server.global.error.exception.ErrorCode;

public class EmojiNotFoundException extends EmoryException {
    public static final EmojiNotFoundException EXCEPTION = new EmojiNotFoundException();

    private EmojiNotFoundException() {
        super(ErrorCode.EMOJI_NOT_FOUND);
    }
}
