package com.arismendy.powertracker.dto;

import java.util.UUID;
import java.time.LocalDateTime;

public record ExerciseEntryDto(
        UUID id,
        String exerciseName,
        int sets,
        String reps,
        double weight,
        double lastSetRpe,
        String notes,
        UUID dayId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}