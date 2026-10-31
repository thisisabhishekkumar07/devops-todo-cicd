package com.example.todoapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final List<Map<String, Object>> todos =
            new CopyOnWriteArrayList<>();

    private final AtomicInteger nextId = new AtomicInteger(1);

    @GetMapping("/todos")
    public List<Map<String, Object>> getTodos() {
        return todos;
    }

    @PostMapping("/todos")
    public Map<String, Object> addTodo(
            @RequestBody Map<String, String> request) {

        Map<String, Object> todo = new HashMap<>();
        todo.put("id", nextId.getAndIncrement());
        todo.put("task", request.get("task"));

        todos.add(todo);
        return todo;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}