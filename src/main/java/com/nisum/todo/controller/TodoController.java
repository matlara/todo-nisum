package com.nisum.todo.controller;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("todos")
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
}
