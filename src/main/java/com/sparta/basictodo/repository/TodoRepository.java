package com.sparta.basictodo.repository;

import com.sparta.basictodo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
