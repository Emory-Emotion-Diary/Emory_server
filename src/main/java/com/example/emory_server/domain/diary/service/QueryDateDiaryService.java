package com.example.emory_server.domain.diary.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.diary.domain.Diary;
import com.example.emory_server.domain.diary.domain.repository.DiaryRepository;
import com.example.emory_server.domain.diary.exception.DiaryNotFoundException;
import com.example.emory_server.domain.diary.presentation.dto.response.DiaryResponse;
import com.example.emory_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryDateDiaryService {
    private final DiaryRepository diaryRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public DiaryResponse execute() {
        User user = userFacade.currentUser();

        Diary diary = diaryRepository.findByUserId(user.getId())
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        return DiaryResponse.builder()
                .id(diary.getId())
                .content(diary.getContent())
                .date(diary.getDate())
                .emojiName(diary.getEmoji().getName())
                .emojiUrl(diary.getEmoji().getS3Url())
                .build();
    }
}
