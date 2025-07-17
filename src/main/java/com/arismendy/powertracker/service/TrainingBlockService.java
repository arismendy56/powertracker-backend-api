package com.arismendy.powertracker.service;

import com.arismendy.powertracker.entities.TrainingBlock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingBlockService {

    // Define methods for managing training blocks
    // For example:
    List<TrainingBlock> getAllTrainingBlocks();

    TrainingBlock createTrainingBlock(TrainingBlock trainingBlock);

    Optional<TrainingBlock> getTrainingBlockById(UUID id);

    TrainingBlock updateTrainingBlock(UUID id, TrainingBlock trainingBlock);

    void deleteTrainingBlock(UUID id);

}