package com.arismendy.powertracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "exercise_entries")
public class ExerciseEntry {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "exercise_sets", nullable = false)
    private int sets;

    @Column(name = "exercise_reps", nullable = false)
    private String reps;

    @Column(name = "exercise_weight", nullable = false)
    private double weight;

    @Column(name = "exercise_last_set_rpe", nullable = false)
    private double lastSetRpe;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "training_day_id")
    private TrainingDay trainingDay;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
