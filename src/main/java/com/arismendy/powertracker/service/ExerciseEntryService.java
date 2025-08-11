package com.arismendy.powertracker.service;

import com.arismendy.powertracker.entities.ExerciseEntry;

import java.util.List;
import java.util.UUID;

public interface ExerciseEntryService {

    // Define methods for the service, e.g., to get all exercises by training day ID
     List<ExerciseEntry> getAllByTrainingDayId(UUID trainingDayId);

    // Other service methods can be defined here as needed
}
