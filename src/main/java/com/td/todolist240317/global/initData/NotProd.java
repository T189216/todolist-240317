package com.td.todolist240317.global.initData;

import com.td.todolist240317.domain.todo.todo.dto.TodoDto;
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
            TodoDto.Request sampleTodo = TodoDto.Request.builder()
                    .content("작업 추가!️")
                    .isCompleted(false)
                    .priority(1)
                    .deadline(LocalDate.now().plusDays(3))
                    .build();

            TodoDto.Request sampleTodo2 = TodoDto.Request.builder()
                    .content("병원 예약하기")
                    .isCompleted(false)
                    .priority(2)
                    .deadline(LocalDate.now().plusDays(5))
                    .build();

            TodoDto.Request sampleTodo3 = TodoDto.Request.builder()
                    .content("여행 계획 짜기...")
                    .isCompleted(false)
                    .priority(1)
                    .deadline(LocalDate.now().plusDays(7))
                    .build();

            todoService.addTodo(sampleTodo);
            todoService.addTodo(sampleTodo2);
            todoService.addTodo(sampleTodo3);
        };
    }
}