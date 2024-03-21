package com.td.todolist240317.domain.todo.todo.controller;

import com.td.todolist240317.domain.todo.todo.entity.Todo;
import com.td.todolist240317.domain.todo.todo.entity.TodoStatus;
import com.td.todolist240317.domain.todo.todo.service.HashtagService;
import com.td.todolist240317.domain.todo.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final HashtagService hashtagService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("todoForm", new TodoForm());
        return "todo/createTodoForm";
    }

    @PostMapping("/add")
    public String add(@Valid TodoForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "todo/createTodoForm";
        }

        // 날짜를 문자열로 변환하여 설정
        form.setDeadline(form.getDeadline().toString());

        // 태그 분리
        String hashtagStr = form.getTag();

        Todo todo = new Todo();
        todo.setContent(form.getContent());
        todo.setDeadline(LocalDate.parse(form.getDeadline())); // 문자열을 다시 LocalDate로 변환하여 설정
        todo.setPriority(form.getPriority());
        todo.setStatus(TodoStatus.진행중);

        todoService.save(todo);
        hashtagService.addHashtags(todo, hashtagStr);
        return "redirect:/";
    }

    // 전체 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<Todo> todos = todoService.findTodos();
        model.addAttribute("todos", todos);
        return "todo/todoList";
    }

    @GetMapping("/{todoId}/update")
    public String updateTodoForm(@PathVariable("todoId") Long todoId, Model model) {
        Optional<Todo> getTodo = todoService.findByTodoId(todoId);
        TodoForm form = new TodoForm();

        if (getTodo.isPresent()) {
            Todo todo = getTodo.get();
            form.setContent(todo.getContent());
            form.setDeadline(todo.getDeadline().toString()); // LocalDate를 문자열로 변환하여 설정
            form.setPriority(todo.getPriority());
        }

        model.addAttribute("form", form);
        return "todo/updateTodoForm";
    }

    @PostMapping("/{todoId}/update")
    public String updateTodo(@PathVariable Long todoId, @ModelAttribute("form") TodoForm form) {
        // 문자열로 변환된 deadline을 다시 LocalDate로 변환하여 설정
        LocalDate deadline = LocalDate.parse(form.getDeadline());

        todoService.updateTodo(todoId, form.getContent(), deadline, form.getPriority());
        return "redirect:/todo/list";
    }

    @GetMapping("/{todoId}/delete")
    public String deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return "redirect:/todo/list";
    }
}