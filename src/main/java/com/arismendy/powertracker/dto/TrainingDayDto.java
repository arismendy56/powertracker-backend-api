package com.arismendy.powertracker.dto;

import com.arismendy.powertracker.entities.ExerciseEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingDayDto(
        UUID id,
        int dayNumber,
        LocalDate date,
        UUID weekId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ExerciseEntry> exerciseEntries
) {}