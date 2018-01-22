package com.nisum.todo.controller;

import com.nisum.todo.dao.TodoDaoImpl;
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
    private TodoDaoImpl service = new TodoDaoImpl();


    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("todos")
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }



    @RequestMapping(value = "/{todoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodo(@PathVariable String todoId) {
        Todo todo = service.getById(todoId);
        ResponseEntity<Todo> response;
        if (todo == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(todo, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo){
        service.add(todo);
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTodo(@RequestBody Todo todo){
        if( service.getById(todo.getId()) != null ){
            service.update(todo);
        }
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String todoId){
        if(todoId != null) {
            service.deleteById(todoId);
        }
    }

}
