package com.arismendy.powertracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "training_weeks")
public class TrainingWeek {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "training_block_id", nullable = false)
    private TrainingBlock trainingBlock;

    @Column(name = "week_number", nullable = false)
    private int weekNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    private List<TrainingDay> trainingDays;

}
