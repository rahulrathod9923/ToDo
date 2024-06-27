package com.todo.controller;

import com.todo.entity.Todo;
import com.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public List<Todo> getAllTasks() {
        return toDoService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTaskById(@PathVariable Long id) {
        return toDoService.getTaskById(id);
    }

    @PostMapping
    public Todo createTask(@RequestBody Todo toDo) {
        return toDoService.saveTask(toDo);
    }

    @PutMapping("/{id}")
    public Todo updateTask(@PathVariable Long id, @RequestBody Todo toDo) {
        return toDoService.updateTask(id, toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        toDoService.deleteTask(id);
    }
}
