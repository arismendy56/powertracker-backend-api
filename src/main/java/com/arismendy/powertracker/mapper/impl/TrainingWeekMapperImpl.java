package com.arismendy.powertracker.mapper.impl;

import com.arismendy.powertracker.dto.TrainingWeekDto;
import com.arismendy.powertracker.entities.TrainingWeek;
import com.arismendy.powertracker.mapper.TrainingDayMapper;
import com.arismendy.powertracker.mapper.TrainingWeekMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrainingWeekMapperImpl implements TrainingWeekMapper {

    private final TrainingDayMapper trainingDayMapper;

    public TrainingWeekMapperImpl(TrainingDayMapper trainingDayMapper) {
        this.trainingDayMapper = trainingDayMapper;
    }
    @Override
    public TrainingWeek fromDto(TrainingWeekDto trainingWeekDto) {
        return new TrainingWeek(
                trainingWeekDto.id(),
                null,
                trainingWeekDto.weekNumber(),
                trainingWeekDto.createdAt(),
                trainingWeekDto.updatedAt(),
                Optional.ofNullable(trainingWeekDto.trainingDays())
                        .map(days -> days.stream().map(trainingDayMapper::fromDto).toList())
                        .orElse(null)
        );
    }

    @Override
    public TrainingWeekDto toDto(TrainingWeek trainingWeek) {
        return new TrainingWeekDto(
                trainingWeek.getId(),
                trainingWeek.getTrainingBlock() != null ? trainingWeek.getTrainingBlock().getId() : null,
                trainingWeek.getWeekNumber(),
                trainingWeek.getCreatedAt(),
                trainingWeek.getUpdatedAt(),
                Optional.ofNullable(trainingWeek.getTrainingDays())
                        .map(days -> days.stream().map(trainingDayMapper::toDto).toList())
                        .orElse(null)
        );
    }

}
