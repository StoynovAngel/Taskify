package com.taskify.taskify.repository;

import com.taskify.taskify.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
