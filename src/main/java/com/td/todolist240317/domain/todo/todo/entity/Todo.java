package com.td.todolist240317.domain.todo.todo.entity;

import com.td.todolist240317.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity(name = "Todo")
@Table(name = "todo")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isCompleted;

    private int priority;

    private LocalDate deadline;
}