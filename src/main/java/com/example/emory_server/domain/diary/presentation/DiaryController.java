package com.example.emory_server.domain.diary.presentation;

import com.example.emory_server.domain.diary.presentation.dto.request.DiaryRequest;
import com.example.emory_server.domain.diary.service.CreateDiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final CreateDiaryService createDiaryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateDiary(@RequestBody @Valid DiaryRequest request) {
        createDiaryService.execute(request);
    }
}
