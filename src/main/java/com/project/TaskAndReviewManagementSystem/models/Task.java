package com.project.TaskAndReviewManagementSystem.models;


import com.project.TaskAndReviewManagementSystem.models.enums.TASKPRIORITY;
import com.project.TaskAndReviewManagementSystem.models.enums.TASKSTATUS;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    String title;
    String description;

    @Enumerated(EnumType.STRING)
    TASKSTATUS status = TASKSTATUS.TODO;

    @Enumerated(EnumType.STRING)
    TASKPRIORITY priority = TASKPRIORITY.LOW;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "assigned_to")
    User assignedBy;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "created_by")
    User createdBy;

    @Column(name = "due_date")
    LocalDate dueDate = LocalDate.now();

    @Column(name = "created_at")
    LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "task")
    List<TaskReviewer> reviews;

}
