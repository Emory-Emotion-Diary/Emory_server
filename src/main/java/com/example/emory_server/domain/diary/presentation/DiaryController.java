package com.example.emory_server.domain.diary.presentation;

import com.example.emory_server.domain.diary.presentation.dto.request.DiaryRequest;
import com.example.emory_server.domain.diary.presentation.dto.response.DiaryResponse;
import com.example.emory_server.domain.diary.service.CreateDiaryService;
import com.example.emory_server.domain.diary.service.DeleteDiaryService;
import com.example.emory_server.domain.diary.service.QueryDateDiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final CreateDiaryService createDiaryService;
    private final DeleteDiaryService deleteDiaryService;
    private final QueryDateDiaryService queryDateDiaryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDiary(@RequestBody @Valid DiaryRequest request) {
        createDiaryService.execute(request);
    }

    @DeleteMapping("/{diary-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiary(@PathVariable("diary-id") Long diaryId) {
        deleteDiaryService.execute(diaryId);
    }

    @GetMapping("/{diary-id}")
    @ResponseStatus(HttpStatus.OK)
    public DiaryResponse queryDiary(@PathVariable Long diaryId) {
        return queryDateDiaryService.execute(diaryId);
    }
}
