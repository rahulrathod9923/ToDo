package com.todo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

@Entity
public class Todo {
    
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String task;
    
    private boolean completed;

    @OneToOne(cascade = CascadeType.ALL)
    private CompletedTask completedTask;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public CompletedTask getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(CompletedTask completedTask) {
        this.completedTask = completedTask;
    }
}
