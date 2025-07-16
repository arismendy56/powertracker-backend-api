package com.arismendy.powertracker.mapper.impl;

import com.arismendy.powertracker.dto.TrainingBlockDto;
import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.mapper.TrainingBlockMapper;
import com.arismendy.powertracker.mapper.TrainingWeekMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrainingBlockMapperImpl implements TrainingBlockMapper {

    private final TrainingWeekMapper trainingWeekMapper;

    public TrainingBlockMapperImpl(TrainingWeekMapper trainingWeekMapper) {
        this.trainingWeekMapper = trainingWeekMapper;
    }

    @Override
    public TrainingBlock fromDto(TrainingBlockDto trainingBlockDto) {
        return new TrainingBlock(
                trainingBlockDto.id(),
                trainingBlockDto.name(),
                trainingBlockDto.startDate(),
                trainingBlockDto.endDate(),
                trainingBlockDto.notes(),
                trainingBlockDto.createdBy(),
                trainingBlockDto.createdAt(),
                trainingBlockDto.updatedAt(),
                Optional.ofNullable(trainingBlockDto.weeks())
                        .map(weeks -> weeks.stream().map(trainingWeekMapper::fromDto).toList()
                        ).orElse(null)
        );
    }

    @Override
    public TrainingBlockDto toDto(TrainingBlock trainingBlock) {
        return new TrainingBlockDto(
                trainingBlock.getId(),
                trainingBlock.getName(),
                trainingBlock.getStartDate(),
                trainingBlock.getEndDate(),
                trainingBlock.getNotes(),
                trainingBlock.getCreatedByUserId(),
                trainingBlock.getCreatedAt(),
                trainingBlock.getUpdatedAt(),
                Optional.ofNullable(trainingBlock.getWeeks())
                        .map(weeks -> weeks.stream().map(trainingWeekMapper::toDto).toList()
                        ).orElse(null)
        );
    }
}
