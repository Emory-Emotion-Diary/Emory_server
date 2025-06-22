package com.example.emory_server.domain.emoji.service;

import com.example.emory_server.domain.auth.service.facade.UserFacade;
import com.example.emory_server.domain.emoji.domain.Emoji;
import com.example.emory_server.domain.emoji.domain.repository.EmojiRepository;
import com.example.emory_server.domain.emoji.exception.EmojiNotFoundException;
import com.example.emory_server.domain.emoji.presentation.dto.request.DeleteEmojiRequest;
import com.example.emory_server.domain.user.domain.User;
import com.example.emory_server.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmojiDeleteService {
    private final EmojiRepository emojiRepository;
    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public void execute(DeleteEmojiRequest request) {
        User user = userFacade.currentUser();

        Emoji emoji = emojiRepository.findByName(request.getName())
                        .orElseThrow(() -> EmojiNotFoundException.EXCEPTION);

        s3Service.delete(emoji.getS3Url());

        emojiRepository.delete(emoji);
    }
}
