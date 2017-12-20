package com.nisum.todo;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner{

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		todoRepository.save(new Todo("Hello",false));
		todoRepository.save(new Todo("Hello1",true));
		todoRepository.save(new Todo("Hello2",false));
		todoRepository.save(new Todo("Hello3",true));
		todoRepository.save(new Todo("Hello4",false));
		todoRepository.save(new Todo("Hello5",true));
		todoRepository.save(new Todo("Hello6",false));
		todoRepository.save(new Todo("Hello7",true));
		todoRepository.save(new Todo("Hello8",false));
		todoRepository.save(new Todo("Hello9",false));
	}
}
