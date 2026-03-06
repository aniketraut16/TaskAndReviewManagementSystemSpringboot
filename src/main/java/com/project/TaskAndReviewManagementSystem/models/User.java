package com.project.TaskAndReviewManagementSystem.models;

import com.project.TaskAndReviewManagementSystem.models.enums.ROLES;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
        name = "Users"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false , unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ROLES role = ROLES.MEMBER;

    @Column( name = "created_at", nullable = false)
    LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "createdBy")
    List<Team> teamsCreatedByMe;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<TeamMember> teams;

    @OneToMany(mappedBy = "assignedBy")
    List<Task> assignedTasks;

    @OneToMany(mappedBy = "createdBy")
    List<Task> createdTasks;

    @OneToMany(mappedBy = "reviewer")
    List<TaskReviewer> reviews;


}

;