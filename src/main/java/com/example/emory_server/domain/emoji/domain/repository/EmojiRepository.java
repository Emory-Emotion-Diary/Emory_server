package com.example.emory_server.domain.emoji.domain.repository;

import com.example.emory_server.domain.emoji.domain.Emoji;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmojiRepository extends CrudRepository<Emoji, Long> {
    Optional<Emoji> findByName(String name);
}
