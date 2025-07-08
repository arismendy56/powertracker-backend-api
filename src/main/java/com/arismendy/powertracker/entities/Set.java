package com.arismendy.powertracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sets")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;

    private Integer setNumber;

    private Double weight;

    private Integer reps;

    private Double rpe;

    private Integer rir;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Set() {}

    public Set(WorkoutExercise workoutExercise, Integer setNumber, Double weight, Integer reps, Double rpe, Integer rir) {
        this.workoutExercise = workoutExercise;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
        this.rpe = rpe;
        this.rir = rir;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters omitted for brevity
}
