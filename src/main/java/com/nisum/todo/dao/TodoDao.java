package com.nisum.todo.dao;

import com.nisum.todo.entity.Todo;

import java.util.List;

public interface TodoDao {

    public List<Todo> getAll();
    public Todo getById();
    public void add();
    public void update();
    public Todo deleteById();

}
