package com.project.TaskAndReviewManagementSystem.repository;

import com.project.TaskAndReviewManagementSystem.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
