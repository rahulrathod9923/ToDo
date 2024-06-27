package com.todo.controller;

import com.todo.entity.CompletedTask;
import com.todo.service.CompletedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/completed")
public class CompletedTaskController {

    @Autowired
    private CompletedTaskService completedTaskService;

    @GetMapping
    public List<CompletedTask> getAllCompletedTasks() {
        return completedTaskService.getAllCompletedTasks();
    }

    @PostMapping
    public CompletedTask createCompletedTask(@RequestBody CompletedTask completedTask) {
        return completedTaskService.saveCompletedTask(completedTask);
    }
}
