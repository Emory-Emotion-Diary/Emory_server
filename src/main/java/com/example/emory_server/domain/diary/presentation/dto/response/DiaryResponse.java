package com.example.emory_server.domain.diary.presentation.dto.response;

import com.example.emory_server.domain.diary.domain.Diary;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DiaryResponse(
        Long id,
        String content,
        String emojiName,
        String emojiUrl,
        LocalDate date
) {
    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getContent(),
                diary.getEmoji().getName(),
                diary.getEmoji().getS3Url(),
                diary.getDate()
        );
    }
}
