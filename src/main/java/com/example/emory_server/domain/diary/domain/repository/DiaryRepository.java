package com.example.emory_server.domain.diary.domain.repository;

import com.example.emory_server.domain.diary.domain.Diary;
import com.example.emory_server.domain.diary.presentation.dto.response.DiaryResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
    Optional<DiaryResponse> findByDate(LocalDate date);

    List<Diary> findByUserIdAndDate(Long userId, LocalDate start, LocalDate end);
}
