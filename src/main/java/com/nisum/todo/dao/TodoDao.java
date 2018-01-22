package com.nisum.todo.dao;

import com.nisum.todo.entity.Todo;

import java.util.List;

public interface TodoDao {

    public List<Todo> getAll();
    public Todo getById(String todoId);
    public void add(Todo todo);
    public void update(Todo todo);
    public Todo deleteById(String todoId);

}
