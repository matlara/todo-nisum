package com.nisum.todo.dao;

import com.nisum.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoDaoMock implements TodoDao {

    private static final String TODO_ID_1 = "01";
    private static final String TODO_TITLE_1 = "Title1";
    private static final Boolean TODO_DONE_1 = true;
    private static final String TODO_ID_2 = "02";
    private static final String TODO_TITLE_2 = "Title2";
    private static final String TODO_TITLE_MODIFIED_2 = "TitleModified2";
    private static final Boolean TODO_DONE_2 = false;
    private static final String TODO_ID_3 = "03";
    private static final String TODO_TITLE_3 = "Title3";
    private static final Boolean TODO_DONE_3 = true;

    private static ArrayList<Todo> todos = new ArrayList<>();

    /*Before*/
    public void setUp(){
        Todo todo1 = new Todo(TODO_TITLE_1, TODO_DONE_1);
        todo1.setId(TODO_ID_1);
        Todo todo2 = new Todo(TODO_TITLE_2, TODO_DONE_2);
        todo2.setId(TODO_ID_2);
        Todo todo3 = new Todo(TODO_TITLE_3, TODO_DONE_3);
        todo3.setId(TODO_ID_3);
        todos.add(todo1);
        todos.add(todo2);
    }


    @Override
    public List<Todo> getAll() {
        setUp();

        return todos;
    }

    @Override
    public Todo getById(String todoId) {
        setUp();

        todoId = TODO_ID_1;
        for (Todo i : todos) {
            if (i.getId().equals(todoId)) { return i; }
        }
        return null;
    }

    @Override
    public void add(Todo todo) {
        setUp();

        todo = new Todo(TODO_TITLE_3, TODO_DONE_3);
        todo.setId(TODO_ID_3);
        todos.add(todo);
    }

    @Override
    public void update(Todo todo) {
        setUp();

        todo = new Todo(TODO_TITLE_2, TODO_DONE_2);
        todo.setId(TODO_ID_2);
        todos.remove(todo.getId());
        todo = new Todo(TODO_TITLE_MODIFIED_2, TODO_DONE_2);
        todo.setId(TODO_ID_2);
        todos.add(todo);
    }

    @Override
    public Todo deleteById(String todoId) {
        setUp();

        todoId = TODO_ID_2;
        Todo todo = new Todo(TODO_TITLE_2, TODO_DONE_2);
        todo.setId(todoId);
        todos.remove(todo.getId());
        return todo;
    }
}
