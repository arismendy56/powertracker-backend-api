package com.arismendy.powertracker.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingWeekDto(
        UUID id,
        UUID trainingBlockId,
        int weekNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<TrainingDayDto> trainingDays
) {}