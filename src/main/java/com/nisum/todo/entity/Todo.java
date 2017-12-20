package com.nisum.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Todo {
    @Id
    private String id;
    private String title;
    private Boolean done;

    public Todo(String title, Boolean done) {
        this.title = title;
        this.done = done;
    }
}
