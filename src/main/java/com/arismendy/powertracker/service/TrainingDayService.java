package com.arismendy.powertracker.service;

import com.arismendy.powertracker.entities.TrainingDay;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingDayService {

    // Define methods for managing training days, such as:
     List<TrainingDay> getTrainingDays(UUID trainingWeekId);
     TrainingDay createTrainingDay(UUID trainingWeekId, TrainingDay trainingDay);
     TrainingDay updateTrainingDay(UUID trainingWeekId, UUID dayId, TrainingDay trainingDayDto);
     void deleteTrainingDay(UUID trainingWeekId, UUID dayId);
     Optional<TrainingDay> getTrainingDayById(UUID trainingWeekId, UUID trainingDayId);
}
