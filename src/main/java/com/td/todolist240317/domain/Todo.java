package com.td.todolist240317.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Todo")
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NonNull
    public String content;
}