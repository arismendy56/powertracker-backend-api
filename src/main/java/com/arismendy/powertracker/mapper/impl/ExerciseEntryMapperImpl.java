package com.arismendy.powertracker.mapper.impl;

import com.arismendy.powertracker.dto.ExerciseEntryDto;
import com.arismendy.powertracker.entities.ExerciseEntry;
import com.arismendy.powertracker.mapper.ExerciseEntryMapper;
import org.springframework.stereotype.Component;

@Component
public class ExerciseEntryMapperImpl implements ExerciseEntryMapper {

    @Override
    public ExerciseEntry fromDto(ExerciseEntryDto exerciseEntryDto) {
        return new ExerciseEntry(
                exerciseEntryDto.id(),
                exerciseEntryDto.exerciseName(),
                exerciseEntryDto.sets(),
                exerciseEntryDto.reps(),
                exerciseEntryDto.weight(),
                exerciseEntryDto.lastSetRpe(),
                exerciseEntryDto.notes(),
                null,
                exerciseEntryDto.createdAt(),
                exerciseEntryDto.updatedAt()
        );
    }

    @Override
    public ExerciseEntryDto toDto(ExerciseEntry exerciseEntry) {
        return new ExerciseEntryDto(
                exerciseEntry.getId(),
                exerciseEntry.getExerciseName(),
                exerciseEntry.getSets(),
                exerciseEntry.getReps(),
                exerciseEntry.getWeight(),
                exerciseEntry.getLastSetRpe(),
                exerciseEntry.getNotes(),
                exerciseEntry.getTrainingDay() != null ? exerciseEntry.getTrainingDay().getId() : null,
                exerciseEntry.getCreatedAt(),
                exerciseEntry.getUpdatedAt()
        );
    }
}
