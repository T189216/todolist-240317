package com.td.todolist240317.domain.todo.todo.controller;

import com.td.todolist240317.domain.todo.todo.dto.TodoDto;
import com.td.todolist240317.domain.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/")
    public String findAllTodos(Model model) {
        List<TodoDto.Response> todos = todoService.findAllTodos();
        model.addAttribute("todos", todos);
        return "todo/todos";
    }

    @PostMapping("/addTodo")
//    @PreAuthorize("isAuthenticated()")
    public String addTodo(@ModelAttribute TodoDto.Request request) {
        todoService.addTodo(request);

        return "redirect:/";
    }
}