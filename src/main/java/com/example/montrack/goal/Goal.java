package com.example.montrack.goal;

import jakarta.persistence.*;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_id_gen")
    @SequenceGenerator(name = "goal_id_gen", sequenceName = "goal_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
}
