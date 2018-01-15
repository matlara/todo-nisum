package com.nisum.todo.dao;

import com.nisum.todo.entity.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TodoDaoImpl implements TodoDao {

    private Map<String, Todo> todos;

    public TodoDaoImpl() {
        todos = new HashMap<String, Todo>();
    }

    public List<Todo> getAll() {
        List<Todo> list = new ArrayList<Todo>();
        for (Map.Entry<String, Todo> entry : todos.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public Todo getById(String todoId){
        return todos.get(todoId);
    }

    public void add(Todo todo){
        todos.put(todo.getId(), todo);
    }

    public void update(Todo todo){
        todos.remove(todo.getId());
        todos.put(todo.getId(), todo);
    }

    public Todo deleteById(String id){
        return todos.remove(id);
    }

}
