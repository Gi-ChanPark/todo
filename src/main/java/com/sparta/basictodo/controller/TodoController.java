package com.sparta.basictodo.controller;

import com.sparta.basictodo.dto.*;
import com.sparta.basictodo.entity.Todo;
import com.sparta.basictodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public TodoSaveResponseDto saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto) {
        return todoService.saveTodo(todoSaveRequestDto);
    }

    @GetMapping("/todos")
    public List<TodoSimpleResponseDto> getTodos(
            @RequestParam String date
    ) {
        return todoService.getTodos(date);
    }

    @GetMapping("/todos/{todoId}")
    public TodoDetailResponseDto getTode(@PathVariable Long todoId) {
        return todoService.getTodo(todoId);
    }

    @PutMapping("/todos/{todoId}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        return todoService.updateTodo(todoId, todoUpdateRequestDto);
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoDeleteRequestDto todoDeleteRequestDto) {
        todoService.deleteTodo(todoId, todoDeleteRequestDto);
    }
}
