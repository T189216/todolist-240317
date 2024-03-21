package com.td.todolist240317.domain.todo.todo.entity;

import com.td.todolist240317.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    private String content;

    private LocalDate deadline; // 마감일 설정

    @Enumerated(EnumType.STRING)
    private TodoPriority priority; // 우선순위 [HIGH, MEDIUM, LOW]

    @Enumerated(EnumType.STRING)
    private TodoStatus status; // 완료 상태 [PROGRESS, DONE]
}