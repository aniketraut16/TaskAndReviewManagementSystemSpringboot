package com.project.TaskAndReviewManagementSystem.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "team_id")
    Team managingTeam;

    LocalDate created_at = LocalDate.now();

    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
    List<Task> tasks;
}
