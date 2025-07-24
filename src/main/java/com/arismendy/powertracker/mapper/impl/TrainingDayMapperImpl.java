package com.arismendy.powertracker.mapper.impl;

import com.arismendy.powertracker.dto.TrainingDayDto;
import com.arismendy.powertracker.entities.TrainingDay;
import com.arismendy.powertracker.mapper.TrainingDayMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingDayMapperImpl implements TrainingDayMapper {

    @Override
    public TrainingDay fromDto(TrainingDayDto trainingDayDto) {
        return new TrainingDay(
                trainingDayDto.id(),
                trainingDayDto.dayNumber(),
                trainingDayDto.date(),
                null,
                trainingDayDto.createdAt(),
                trainingDayDto.updatedAt(),
               null
        );
    }

    @Override
    public TrainingDayDto toDto(TrainingDay trainingDay) {
        return new TrainingDayDto(
                trainingDay.getId(),
                trainingDay.getDayNumber(),
                trainingDay.getDate(),
                trainingDay.getWeek() != null ? trainingDay.getWeek().getId() : null,
                trainingDay.getCreatedAt(),
                trainingDay.getUpdatedAt(),
                trainingDay.getExercises()
        );
    }
}
