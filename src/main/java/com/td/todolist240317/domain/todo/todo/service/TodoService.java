package com.td.todolist240317.domain.todo.todo.service;

import com.td.todolist240317.domain.todo.todo.dto.TodoDto;
import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public List<TodoDto.Response> findAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        List<TodoDto.Response> responseDtoList = todos.stream().map(e -> TodoDto.Response.builder()
                .id(e.getId())
                .content(e.getContent())
                .isCompleted(e.isCompleted())
                .priority(e.getPriority())
                .deadline(e.getDeadline())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build()).collect(Collectors.toList());

        return responseDtoList;
    }

    @Transactional
    public void addTodo(TodoDto.Request request) {
        Todo todo = Todo.builder()
                .content(request.getContent())
                .isCompleted(request.isCompleted())
                .priority(request.getPriority())
                .deadline(request.getDeadline())
                .build();

        todoRepository.save(todo);
    }

    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ExpressionException ("Todo not found with id: " + id));
    }
}