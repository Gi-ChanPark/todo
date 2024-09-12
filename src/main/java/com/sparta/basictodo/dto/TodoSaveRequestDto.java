package com.sparta.basictodo.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String todo;
    private String managerName;
    private String password;
}
