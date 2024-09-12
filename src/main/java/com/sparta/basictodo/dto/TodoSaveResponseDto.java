package com.sparta.basictodo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String todo;
    private final String managerName;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;


    public TodoSaveResponseDto(Long id, String todo, String managerName, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
