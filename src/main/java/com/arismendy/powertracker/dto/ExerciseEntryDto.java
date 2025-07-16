package com.arismendy.powertracker.dto;

import java.util.UUID;
import java.time.LocalDateTime;

public record ExerciseEntryDto(
        UUID id,
        String exerciseName,
        int sets,
        int reps,
        double weight,
        UUID dayId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}