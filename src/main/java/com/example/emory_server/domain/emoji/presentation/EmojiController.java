package com.example.emory_server.domain.emoji.presentation;

import com.example.emory_server.domain.emoji.presentation.dto.request.DeleteEmojiRequest;
import com.example.emory_server.domain.emoji.presentation.dto.request.EmojiRequest;
import com.example.emory_server.domain.emoji.service.EmojiDeleteService;
import com.example.emory_server.domain.emoji.service.EmojiUploadService;
import com.example.emory_server.infrastructure.s3.service.S3Service;
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
    private final S3Service s3Service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadEmoji(@RequestPart(name = "file") MultipartFile file,
                            @RequestPart(name = "request") @Valid EmojiRequest request) {
        emojiUploadService.execute(request, file);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmoji(@RequestBody @Valid DeleteEmojiRequest request) {
        emojiDeleteService.execute(request);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestPart(value = "image") MultipartFile file) {
        s3Service.upload(file);
    }
}
