package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.ExerciseEntry;
import com.arismendy.powertracker.repositories.ExerciseEntryRepository;
import com.arismendy.powertracker.service.ExerciseEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseEntryServiceImpl implements ExerciseEntryService {

    private final ExerciseEntryRepository exerciseEntryRepository;

    public ExerciseEntryServiceImpl(ExerciseEntryRepository exerciseEntryRepository) {
        this.exerciseEntryRepository = exerciseEntryRepository;
    }

    @Override
    public List<ExerciseEntry> getAllByTrainingDayId(UUID trainingDayId) {
        return exerciseEntryRepository.findAllByTrainingDayId(trainingDayId);
    }
}
