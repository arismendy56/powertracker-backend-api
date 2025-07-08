package com.arismendy.powertracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer orderInWorkout;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public WorkoutExercise() {}

    public WorkoutExercise(Workout workout, Exercise exercise, Integer orderInWorkout, String notes) {
        this.workout = workout;
        this.exercise = exercise;
        this.orderInWorkout = orderInWorkout;
        this.notes = notes;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
