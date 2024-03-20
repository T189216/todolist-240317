package com.td.todolist240317.global.initData;

import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Profile("!prod")
@Configuration
@Slf4j
@RequiredArgsConstructor
public class NotProd {
    private final TodoService todoService;

    @Bean
    @Order(2)
    public ApplicationRunner initNotProd() {
        return args -> {
            Todo todo1 = Todo.addTodo("장보기", LocalDate.now().plusDays(3));
            Todo todo2 = Todo.addTodo("병원 예약", LocalDate.now().plusDays(5));
            Todo todo3 = Todo.addTodo("여행 계획", LocalDate.now().plusDays(10));

            todoService.save(todo1);
            todoService.save(todo2);
            todoService.save(todo3);
        };
    }
}