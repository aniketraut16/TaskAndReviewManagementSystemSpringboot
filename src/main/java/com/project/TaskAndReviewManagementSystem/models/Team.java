package com.project.TaskAndReviewManagementSystem.models;


import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
        name = "Teams"
)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "created_by")
    User createdBy;

    @Column(name = "created_at")
    LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "managingTeam" , cascade = CascadeType.ALL)
    List<Project> projects;

    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
    List<TeamMember> members;


}
