package com.ua.glebkorobov.todolist.services;

import com.ua.glebkorobov.todolist.dto.Status;
import com.ua.glebkorobov.todolist.dto.TodoDto;
import com.ua.glebkorobov.todolist.entities.TodoEntity;
import com.ua.glebkorobov.todolist.entities.User;
import com.ua.glebkorobov.todolist.repositories.TodoRepository;
import com.ua.glebkorobov.todolist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    public List<TodoEntity> getTodoList() {
        Optional<User> userOptional = userRepository.findByEmail(getUserEmail());
        if (userOptional.isPresent()) {
            return todoRepository.findByUser(userOptional.get());
        } else {
            return List.of();
        }
    }

    public Optional<TodoEntity> getById(long id) {
        Optional<User> userOptional = userRepository.findByEmail(getUserEmail());
        if (userOptional.isPresent()) {
            return todoRepository.findByIdAndUser(id, userOptional.get());
        } else {
            return Optional.empty();
        }
    }


    public Optional<TodoEntity> save(TodoDto todoDto) {
        Optional<User> user = userRepository.findByEmail(getUserEmail());
        return user.map(value -> todoRepository.save(
                new TodoEntity(todoDto.getTask(), todoDto.getDate(), todoDto.getStatus(), value)));
    }


    public Optional<TodoEntity> updateTask(long id, String task, Status status) {
        Optional<User> userOptional = userRepository.findByEmail(getUserEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return Optional.empty();
        }
        Optional<TodoEntity> todoEntity = todoRepository.findByIdAndUser(id, user);
        if (todoEntity.isPresent()) {
            TodoEntity todo = todoEntity.get();
            if (task != null) {
                todo.setTask(task);
            }
            if (status != null && todo.getStatus().changeStatus(status)) {
                todo.setStatus(status);
            } else {
                return Optional.empty();
            }
            return Optional.of(todoRepository.save(todo));
        } else {
            return Optional.empty();
        }
    }

    public Optional<TodoEntity> deleteTask(long id) {
        Optional<User> userOptional = userRepository.findByEmail(getUserEmail());
        if (userOptional.isPresent()) {
            return todoRepository.deleteTodoEntityByIdAndUser(id, userOptional.get());
        } else {
            return Optional.empty();
        }
    }


    private String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
