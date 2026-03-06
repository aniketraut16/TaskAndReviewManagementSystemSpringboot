package com.project.TaskAndReviewManagementSystem.repository;

import com.project.TaskAndReviewManagementSystem.models.TaskReviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReviewerRepository extends JpaRepository<TaskReviewer,Long> {
}
