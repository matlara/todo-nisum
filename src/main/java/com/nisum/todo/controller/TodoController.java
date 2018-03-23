package com.nisum.todo.controller;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("todos")
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @RequestMapping(value = "todos/{todoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Todo> getTodo(@PathVariable String todoId) {
        ResponseEntity<Todo> response;
        Todo todo = todoRepository.findOne(todoId);
        if (todo != null) {
            response = new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(todo, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @RequestMapping(value = "todos/{todoId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateTodo(@RequestBody String todoId) {
        if (todoRepository.exists(todoId)) {
            Todo todo = todoRepository.findOne(todoId);
            todoRepository.save(todo);
        }
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable String todoId) {
        if (todoRepository.exists(todoId)) {
            todoRepository.delete(todoId);
        }
    }

}