package com.td.todolist240317.domain.todo.todo.controller;

import com.td.todolist240317.domain.todo.todo.dto.TodoDto;
import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    private List<TodoDto> getTodoDtos(Boolean isCompleted) {
        List<Todo> list = todoService.findByIsCompleted(isCompleted);
        return list.stream().map(todo ->
                        new TodoDto(todo.getId(), todo.getContent(), todo.getIsCompleted(),
                                todo.getCreatedAt(), todo.getUpdatedAt(), todo.getDeadline()))
                .collect(Collectors.toList());
    }

    @GetMapping("/todo")
    public String todo(Model model) {
        model.addAttribute("todoDtos", getTodoDtos(false));
        model.addAttribute("completedDtos", getTodoDtos(true));
        return "todo/main";
    }

    @GetMapping("/todo/add")
    public String addForm(@ModelAttribute("todoDto") TodoDto todoDto) {
        return "todo/add";
    }

    @PostMapping("/todos")
    public String addTodo(@ModelAttribute("todoDto") TodoDto todoDto) {
        Optional<Todo> addTodo = Optional.of(Todo.addTodo(
                todoDto.getContent(), todoDto.getDeadline()
        ));
        addTodo.ifPresent(todo -> todoService.save(todo));

        return "redirect:/todo";
    }

    @GetMapping("/todo/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        if (id != null) {
            Optional<Todo> todo = todoService.findById(id);
            todo.ifPresent(todoDto -> model.addAttribute("todoDto", todoDto));
        } else {
            return "redirect:/todo";
        }

        return "todo/edit";
    }

    @PostMapping("/todos/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("todoDto") TodoDto todoDto) {
        if (id != null)
            todoService.update(id, todoDto.getContent(), todoDto.getDeadline());
        return "redirect:/todo";
    }

    // 완료 여부 변경
    @PatchMapping("/todos/{id}")
    public String change(@PathVariable Long id) {
        if (id != null) todoService.changeStatus(id);
        return "redirect:/todo";
    }
}