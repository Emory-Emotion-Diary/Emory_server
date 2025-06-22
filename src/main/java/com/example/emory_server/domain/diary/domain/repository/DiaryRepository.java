package com.example.emory_server.domain.diary.domain.repository;

import com.example.emory_server.domain.diary.domain.Diary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
}
