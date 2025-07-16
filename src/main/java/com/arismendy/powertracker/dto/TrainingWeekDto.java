package com.arismendy.powertracker.dto;

import com.arismendy.powertracker.entities.TrainingDay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingWeekDto(
        UUID id,
        UUID trainingBlockId,
        int weekNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<TrainingDay> trainingDays
) {}