package com.example.emory_server.domain.diary.domain.repository;

import com.example.emory_server.domain.diary.domain.Diary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
    Optional<Diary> findByUserId(Long userId);

    List<Diary> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
