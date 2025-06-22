package com.example.emory_server.domain.emoji.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.emoji.domain.Emoji;
import com.example.emory_server.domain.emoji.domain.repository.EmojiRepository;
import com.example.emory_server.domain.emoji.presentation.dto.request.EmojiRequest;
import com.example.emory_server.domain.user.domain.User;
import com.example.emory_server.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmojiUploadService {
    private final EmojiRepository emojiRepository;
    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public void execute(EmojiRequest request, MultipartFile file) {
        User user = userFacade.currentUser();

        String s3Url = s3Service.upload(file);

        emojiRepository.save(Emoji.builder()
                        .name(request.getName())
                        .s3Url(s3Url)
                .build());
    }
}
