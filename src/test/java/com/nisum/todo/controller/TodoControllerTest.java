package com.nisum.todo.controller;

import com.nisum.todo.dao.TodoDaoImpl;
import com.nisum.todo.entity.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.boot.test.SpringApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class TodoControllerTest {

    Gson gson;
    Properties properties;
    Todo todo1, todo2, todo3;
    List<Todo> todos;
    @Mock
    private TodoDaoImpl service = new TodoDaoImpl();
    @InjectMocks
    TodoController todoController;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        properties = new Properties();

        todo1 = new Todo("1","title test", true);
        todo2 = new Todo("2","title2", false);
        todo3 = new Todo("3","title3", true);

        todos = new ArrayList<Todo>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        MockitoAnnotations.initMocks(this);

        Mockito.when(service.getById(todo1.getId())).thenReturn(todo1);
        Mockito.when(service.getById(todo2.getId())).thenReturn(todo2);
        Mockito.when(service.getById(todo3.getId())).thenReturn(todo3);

        Mockito.when(service.deleteById(todo1.getId())).thenReturn(todo1);
        Mockito.when(service.deleteById(todo2.getId())).thenReturn(todo2);
        Mockito.when(service.deleteById(todo3.getId())).thenReturn(todo3);
        Mockito.when(service.deleteById("4")).thenReturn(null);

        Mockito.when(service.getAll()).thenReturn(todos);

        mvc = MockMvcBuilders.standaloneSetup(new TodoController()).build();
    }


    // test create (POST)
    @Test
    public void createTodo() throws Exception {
        todos = new ArrayList<Todo>();
        String json = gson.toJson(todo1);
        mvc.perform(
                post("/todos/").contentType(
                        MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(todo1.getTitle()))
                .andExpect(jsonPath("$.done").value(todo1.getDone()))
                .andReturn();
    }

    // test get (GET) after create an element
    @Test
    public void createAndGetTodoWithList() throws Exception {
        todos = new ArrayList<Todo>();
        String json = gson.toJson(todo1);
        mvc.perform(
                post("/todos/").contentType(
                        MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(todo1.getId()))
                .andExpect(jsonPath("$.title").value(todo1.getTitle()))
                .andExpect(jsonPath("$.done").value(todo1.getDone()));

        mvc.perform(
                get("/todos/").accept(
                        MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .string("[{" +
                                        "\"id\":1," +
                                        "\"title\":\"title test\"," +
                                        "\"done\":\"true\"}]"));

    }

    // Test update (PUT) an existing element
    @Test
    public void updateAnExistTodo() throws Exception {
        mvc.perform(put("/todo/").contentType(
                MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
    }

    // Test delete (DELETE) an existing element
    @Test
    public void deleteAnExistTodo() throws Exception {
        todos = new ArrayList<Todo>();
        String json = gson.toJson(todo3);
        String todoIdToDelete;

        mvc.perform(
                post("/todos/").contentType(
                        MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(todo3.getId()))
                .andExpect(jsonPath("$.title").value(todo3.getTitle()))
                .andExpect(jsonPath("$.done").value(todo3.getDone()))
                .andReturn();

        properties = gson.fromJson(json, Properties.class);
        todoIdToDelete = properties.getProperty("id");

        mvc.perform(
                delete("/todos/" + todoIdToDelete).accept(
                        MediaType.APPLICATION_JSON))
                // To check the response code. We expect 200 (OK)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(todo3.getId()))
                .andExpect(jsonPath("$.title").value(todo3.getTitle()))
                .andExpect(jsonPath("$.done").value(todo3.getDone()));
    }



}
