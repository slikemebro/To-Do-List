package com.ua.glebkorobov.todolist.repositories;

import com.ua.glebkorobov.todolist.entities.TodoEntity;
import com.ua.glebkorobov.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUser(User user);

    Optional<TodoEntity> findByIdAndUser(long id, User user);

    Optional<TodoEntity> deleteTodoEntityByIdAndUser(long id, User user);
}
