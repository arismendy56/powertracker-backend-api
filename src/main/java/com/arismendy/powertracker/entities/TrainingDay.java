package com.arismendy.powertracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "training_days")
public class TrainingDay {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "day_number", nullable = false)
    private int dayNumber; // 1=Monday, etc. Not neccessarily Day 1 Day 2 just means the day of the week in the training week

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    private TrainingWeek week;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<ExerciseEntry> exercises;
}
