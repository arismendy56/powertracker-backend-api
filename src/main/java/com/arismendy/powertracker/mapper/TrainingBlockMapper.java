package com.arismendy.powertracker.mapper;

import com.arismendy.powertracker.dto.TrainingBlockDto;
import com.arismendy.powertracker.entities.TrainingBlock;

public interface TrainingBlockMapper {

    // Define methods for mapping between TrainingBlock and its DTO representation
    // For example:
     TrainingBlock fromDto(TrainingBlockDto trainingBlockDto);
     TrainingBlockDto toDto(TrainingBlock trainingBlock);

}
