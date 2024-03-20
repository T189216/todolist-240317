package com.td.todolist240317.domain.todo.todo.repository;

import com.td.todolist240317.domain.todo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByIsCompleted(Boolean isCompleted);
}