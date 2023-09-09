package com.ua.glebkorobov.todolist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.glebkorobov.todolist.dto.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
@Data
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public TodoEntity(String task, LocalDate date, Status status, User user) {
        this.task = task;
        this.date = date;
        this.status = status;
        this.user = user;
    }

    public TodoEntity() {
    }
}
