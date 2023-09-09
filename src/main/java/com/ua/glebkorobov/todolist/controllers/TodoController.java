package com.ua.glebkorobov.todolist.controllers;

import com.ua.glebkorobov.todolist.dto.Status;
import com.ua.glebkorobov.todolist.dto.TodoDto;
import com.ua.glebkorobov.todolist.entities.TodoEntity;
import com.ua.glebkorobov.todolist.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.ua.glebkorobov.todolist.util.ResponseUtils.deleteResponse;
import static com.ua.glebkorobov.todolist.util.ResponseUtils.getResponse;
import static com.ua.glebkorobov.todolist.util.ResponseUtils.postResponse;
import static com.ua.glebkorobov.todolist.util.ResponseUtils.updateResponse;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<Collection<TodoEntity>> getTodoList() {
        return getResponse(todoService.getTodoList());
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoEntity> getTodoById(@PathVariable("id") long id) {
        return getResponse(todoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TodoEntity> postTodoTask(@RequestBody TodoDto todoDto) {
        return postResponse(todoService.save(todoDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoEntity> putTodoTask(
            @PathVariable("id") long id,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) Status status) {

        return updateResponse(todoService.updateTask(id, task, status));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoEntity> deleteTodoTask(@PathVariable("id") long id) {
        return deleteResponse(todoService.deleteTask(id));
    }
}