package com.example.emory_server.domain.emoji.presentation;

import com.example.emory_server.domain.emoji.presentation.dto.request.DeleteEmojiRequest;
import com.example.emory_server.domain.emoji.presentation.dto.request.EmojiRequest;
import com.example.emory_server.domain.emoji.service.EmojiDeleteService;
import com.example.emory_server.domain.emoji.service.EmojiUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/emoji")
@RequiredArgsConstructor
public class EmojiController {
    private final EmojiUploadService emojiUploadService;
    private final EmojiDeleteService emojiDeleteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void UploadEmoji(@RequestPart(name = "file") MultipartFile file,
                            @RequestPart(name = "request") @Valid EmojiRequest request) {
        emojiUploadService.execute(request, file);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteEmoji(@RequestBody @Valid DeleteEmojiRequest request) {
        emojiDeleteService.execute(request);
    }
}
