package com.arismendy.powertracker.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingBlockDto(
        UUID id,
        @NotBlank String name,
        LocalDate startDate,
        LocalDate endDate,
        String notes,
        Long createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<TrainingWeekDto> weeks
) {
}
