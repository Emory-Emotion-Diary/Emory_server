package com.example.emory_server.domain.diary.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.diary.domain.Diary;
import com.example.emory_server.domain.diary.domain.repository.DiaryRepository;
import com.example.emory_server.domain.diary.domain.util.MonthlyDateRange;
import com.example.emory_server.domain.diary.presentation.dto.response.DiaryResponse;
import com.example.emory_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryMonthDiaryService {
    private final UserFacade userFacade;
    private final DiaryRepository diaryRepository;

    @Transactional(readOnly = true)
    public List<DiaryResponse> execute() {
        User user = userFacade.currentUser();

        MonthlyDateRange range = MonthlyDateRange.of(LocalDate.now());

        List<Diary> diary = diaryRepository.findByUserIdAndDateBetween(user.getId(), range.getStartDate(), range.getEndDate());

        return diary.stream()
                .map(DiaryResponse::from)
                .collect(Collectors.toList());
    }
}
