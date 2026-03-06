package com.project.TaskAndReviewManagementSystem.repository;

import com.project.TaskAndReviewManagementSystem.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
