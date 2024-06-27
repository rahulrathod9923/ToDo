package com.todo.repo;

import com.todo.entity.CompletedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long> {
}
