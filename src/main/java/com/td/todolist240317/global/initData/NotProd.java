package com.td.todolist240317.global.initData;

import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.entity.TodoPriority;
import com.td.todolist240317.domain.todo.todo.entity.TodoStatus;
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
            Todo t1 = new Todo();
            t1.setContent("장보기~");
            t1.setDeadline(LocalDate.now().plusDays(2));
            t1.setPriority(TodoPriority.낮음);
            t1.setStatus(TodoStatus.진행중);
            todoService.save(t1);

            Todo t2 = new Todo();
            t2.setContent("병원 예약");
            t2.setDeadline(LocalDate.now().plusDays(7));
            t2.setPriority(TodoPriority.중간);
            t2.setStatus(TodoStatus.완료);
            todoService.save(t2);

            Todo t3 = new Todo();
            t3.setContent("레포트 제출");
            t3.setDeadline(LocalDate.now().plusDays(10));
            t3.setPriority(TodoPriority.높음);
            t3.setStatus(TodoStatus.진행중);
            todoService.save(t3);
        };
    }
}