package com.todo.service;

import com.todo.entity.Todo;
import com.todo.repo.ToDoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository todoRepository;

    public List<Todo> getAllTasks() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTaskById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo saveTask(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTask(Long id, Todo todo) {
        Optional<Todo> existingTask = todoRepository.findById(id);
        if (existingTask.isPresent()) {
            Todo updatedTask = existingTask.get();
            updatedTask.setTask(todo.getTask());
            updatedTask.setCompleted(todo.isCompleted());
            return todoRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        todoRepository.deleteById(id);
    }
}
