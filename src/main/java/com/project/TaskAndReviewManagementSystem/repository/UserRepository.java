package com.project.TaskAndReviewManagementSystem.repository;

import com.project.TaskAndReviewManagementSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User,Long> {
}
