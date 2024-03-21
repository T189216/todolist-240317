package com.td.todolist240317.domain.todo.todo.repository;

import com.td.todolist240317.domain.todo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}