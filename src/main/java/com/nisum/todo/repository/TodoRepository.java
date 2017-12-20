package com.nisum.todo.repository;

import com.nisum.todo.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
}
