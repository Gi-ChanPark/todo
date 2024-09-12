package com.sparta.basictodo.service;

import com.sparta.basictodo.dto.*;
import com.sparta.basictodo.entity.Todo;
import com.sparta.basictodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo todo = new Todo(todoSaveRequestDto.getTodo(), todoSaveRequestDto.getManagerName(), todoSaveRequestDto.getPassword());

        Todo saveTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                saveTodo.getId(),
                saveTodo.getTodo(),
                saveTodo.getManagerName(),
                saveTodo.getCreatedAt(),
                saveTodo.getModifiedAt()
        );
    }

    public List<TodoSimpleResponseDto> getTodos(String date) {
        LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

        List<Todo> todoList = todoRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);

        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(todo.getId(), todo.getTodo(), todo.getManagerName(), todo.getCreatedAt(), todo.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("일정이 없습니다."));

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("일정이 없습니다."));

        todo.update(todoUpdateRequestDto.getTodo(), todoUpdateRequestDto.getManagerName());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteRequestDto todoDeleteRequestDto) {
        String password = todoDeleteRequestDto.getPassword();
        if (password == null) {
            throw new NullPointerException("비밀번호가 존재하지 않습니다.");
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("일정이 없습니다."));

        if (password.equals(todo.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        todoRepository.deleteById(todoId);
    }
}
