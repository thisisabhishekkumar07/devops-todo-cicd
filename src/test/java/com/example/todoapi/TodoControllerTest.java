package com.example.todoapi;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoControllerTest {

    private TodoController controller;

    @BeforeEach
    void setUp() {
        controller = new TodoController();
    }

    @Test
    void testHealthEndpoint() {
        Map<String, String> response = controller.health();

        assertEquals("UP", response.get("status"));
    }

    @Test
    void testAddTodo() {
        Map<String, Object> todo =
                controller.addTodo(Map.of("task", "Learn Jenkins"));

        assertEquals(1, todo.get("id"));
        assertEquals("Learn Jenkins", todo.get("task"));
    }

    @Test
    void testGetTodos() {
        controller.addTodo(Map.of("task", "Study DevOps"));

        List<Map<String, Object>> todos = controller.getTodos();

        assertEquals(1, todos.size());
        assertEquals("Study DevOps", todos.get(0).get("task"));
    }
}