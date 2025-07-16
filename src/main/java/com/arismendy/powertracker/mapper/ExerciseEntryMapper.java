package com.arismendy.powertracker.mapper;

import com.arismendy.powertracker.dto.ExerciseEntryDto;
import com.arismendy.powertracker.entities.ExerciseEntry;

public interface ExerciseEntryMapper {
    // Define methods for mapping between ExerciseEntry and its DTO representation
    // For example:
     ExerciseEntry fromDto(ExerciseEntryDto exerciseEntryDto);
     ExerciseEntryDto toDto(ExerciseEntry exerciseEntry);


}
