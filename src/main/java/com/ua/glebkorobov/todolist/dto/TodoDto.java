package com.ua.glebkorobov.todolist.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    @NotBlank
    private String task;

    @Future
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
}
