package com.project.TaskAndReviewManagementSystem.repository;

import com.project.TaskAndReviewManagementSystem.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember,Long> {
}
