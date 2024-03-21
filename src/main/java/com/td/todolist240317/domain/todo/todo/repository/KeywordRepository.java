package com.td.todolist240317.domain.todo.todo.repository;

import com.td.todolist240317.domain.todo.todo.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByContent(String keywordContent);
}