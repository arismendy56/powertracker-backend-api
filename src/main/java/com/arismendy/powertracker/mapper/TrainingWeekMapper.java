package com.arismendy.powertracker.mapper;

import com.arismendy.powertracker.dto.TrainingWeekDto;
import com.arismendy.powertracker.entities.TrainingWeek;


public interface TrainingWeekMapper {

    // Define methods for mapping between TrainingWeek and its DTO representation
    // For example:
     TrainingWeek fromDto(TrainingWeekDto trainingWeekDto);
     TrainingWeekDto toDto(TrainingWeek trainingWeek);

    // Add any additional methods needed for the mapping process
}
