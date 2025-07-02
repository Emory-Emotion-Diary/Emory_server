package com.example.emory_server.domain.diary.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.diary.domain.Diary;
import com.example.emory_server.domain.diary.domain.repository.DiaryRepository;
import com.example.emory_server.domain.diary.presentation.dto.request.DiaryRequest;
import com.example.emory_server.domain.emoji.domain.Emoji;
import com.example.emory_server.domain.emoji.domain.repository.EmojiRepository;
import com.example.emory_server.domain.emoji.exception.EmojiNotFoundException;
import com.example.emory_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDiaryService {
    private final DiaryRepository diaryRepository;
    private final EmojiRepository emojiRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(DiaryRequest request) {
        User user = userFacade.currentUser();

        Emoji emoji = emojiRepository.findByName(request.getEmojiName())
                .orElseThrow(() -> EmojiNotFoundException.EXCEPTION);

        diaryRepository.save(Diary.builder()
                        .content(request.getContent())
                        .emoji(emoji)
                        .user(user)
                .build());
    }
}
