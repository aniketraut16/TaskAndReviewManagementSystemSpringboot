package com.project.TaskAndReviewManagementSystem.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "team_id")
    Team team;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name =  "user_id")
    User user;

    @Column(name = "joined_at")
    LocalDate joinedAt = LocalDate.now();


}
