package com.arismendy.powertracker.service;

import com.arismendy.powertracker.entities.TrainingWeek;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingWeekService {

    // Define methods for managing training weeks, such as:
     List<TrainingWeek> getTrainingWeeks(UUID trainingBlockId);
     TrainingWeek createTrainingWeek(UUID trainingBlockId, TrainingWeek trainingWeek);
     TrainingWeek updateTrainingWeek(UUID trainingBlockId, UUID weekId, TrainingWeek trainingWeekDto);
     void deleteTrainingWeek(UUID trainingBlockId, UUID weekId);
     Optional<TrainingWeek> getTrainingWeekById(UUID trainingBlockId, UUID trainingWeekId);
}
