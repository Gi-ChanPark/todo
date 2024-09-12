package com.sparta.basictodo.repository;

import com.sparta.basictodo.entity.Todo;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCreatedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
