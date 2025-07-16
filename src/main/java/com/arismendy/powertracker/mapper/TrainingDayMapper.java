package com.arismendy.powertracker.mapper;

import com.arismendy.powertracker.dto.TrainingDayDto;
import com.arismendy.powertracker.entities.TrainingDay;

public interface TrainingDayMapper {

    // Define methods for mapping between TrainingDay and its DTO representation
    // For example:
     TrainingDay fromDto(TrainingDayDto trainingDayDto);
     TrainingDayDto toDto(TrainingDay trainingDay);

    // Add any additional methods needed for your specific use case
}
