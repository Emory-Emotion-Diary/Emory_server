package com.example.emory_server.domain.emoji.presentation.dto.request;

import lombok.Getter;

@Getter
public class DeleteEmojiRequest {
    private Long id;
    private String name;
}
