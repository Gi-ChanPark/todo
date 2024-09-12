package com.sparta.basictodo.controller;

import com.sparta.basictodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
}
