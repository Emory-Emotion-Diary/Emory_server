package com.example.emory_server.domain.diary.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.diary.domain.Diary;
import com.example.emory_server.domain.diary.domain.repository.DiaryRepository;
import com.example.emory_server.domain.diary.exception.DiaryNotFoundException;
import com.example.emory_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteDiaryService {
    private final DiaryRepository diaryRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long diaryId) {
        User user = userFacade.currentUser();

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        diaryRepository.delete(diary);
    }
}
