package com.example.emory_server.domain.user.domain.repository;

import com.example.emory_server.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
