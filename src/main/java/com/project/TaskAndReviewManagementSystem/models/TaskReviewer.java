package com.project.TaskAndReviewManagementSystem.models;

import com.project.TaskAndReviewManagementSystem.models.enums.REVIEWSTATUS;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"task_id","reviewer_id"})
        }
)
public class TaskReviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "task_id")
    Task task;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "reviewer_id")
    User reviewer;

    @Column(name = "review_status")
    REVIEWSTATUS reviewStatus;

    Integer rating;

    String feedback;

    @Column(name = "reviewed_at")
    LocalDate reviewedAt = LocalDate.now();
}
