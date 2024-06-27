package com.todo.service;

import com.todo.entity.CompletedTask;
import com.todo.repo.CompletedTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletedTaskService {

    @Autowired
    private CompletedTaskRepository completedTaskRepository;

    public List<CompletedTask> getAllCompletedTasks() {
        return completedTaskRepository.findAll();
    }

    public CompletedTask saveCompletedTask(CompletedTask completedTask) {
        return completedTaskRepository.save(completedTask);
    }
}
