package com.td.todolist240317.controller;

import com.td.todolist240317.domain.Todo;
import com.td.todolist240317.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final ToDoRepository toDoRepository;

    @GetMapping("/")
    public String main(Model model) {
        List<Todo> todos = toDoRepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo") String todo) {
        Todo toDo = new Todo();
        toDo.setTodo(todo);
        toDoRepository.save(toDo);
        return "redirect:/";
    }
}