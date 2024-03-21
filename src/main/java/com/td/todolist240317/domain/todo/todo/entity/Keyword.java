package com.td.todolist240317.domain.todo.todo.entity;

import com.td.todolist240317.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Keyword extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String content;
}