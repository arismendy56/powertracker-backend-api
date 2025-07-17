package com.arismendy.powertracker.mapper;

import com.arismendy.powertracker.dto.ExerciseEntryDto;
import com.arismendy.powertracker.entities.ExerciseEntry;

public interface ExerciseEntryMapper {

     ExerciseEntry fromDto(ExerciseEntryDto exerciseEntryDto);
     ExerciseEntryDto toDto(ExerciseEntry exerciseEntry);


}
