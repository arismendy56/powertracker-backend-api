package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingWeek;
import com.arismendy.powertracker.repositories.TrainingBlockRepository;
import com.arismendy.powertracker.repositories.TrainingWeekRepository;
import com.arismendy.powertracker.service.TrainingWeekService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingWeekServiceImpl implements TrainingWeekService {

    private final TrainingBlockRepository trainingBlockRepository;
    private final TrainingWeekRepository trainingWeekRepository;

    public TrainingWeekServiceImpl(TrainingBlockRepository trainingBlockRepository, TrainingWeekRepository trainingWeekRepository) {
        this.trainingBlockRepository = trainingBlockRepository;
        this.trainingWeekRepository = trainingWeekRepository;
    }
    @Override
    public List<TrainingWeek> getTrainingWeeks(UUID trainingBlockId) {
        return trainingWeekRepository.findByTrainingBlockId(trainingBlockId);
    }

    @Override
    public TrainingWeek createTrainingWeek(UUID trainingBlockId, TrainingWeek trainingWeek) {
        if(trainingWeek.getId() != null) {
            throw new IllegalArgumentException("Training week already has an ID!");
        }
        return trainingBlockRepository.findById(trainingBlockId)
                .map(trainingBlock -> {
                    trainingWeek.setTrainingBlock(trainingBlock);
                    return trainingWeekRepository.save(trainingWeek);
                })
                .orElseThrow(() -> new IllegalArgumentException("Training block not found with id: " + trainingBlockId));
    }

    @Override
    public TrainingWeek updateTrainingWeek(UUID trainingBlockId, UUID weekId, TrainingWeek trainingWeek) {
        return trainingWeekRepository.findByIdAndTrainingBlockId(weekId, trainingBlockId)
                .map(existingWeek -> {
                    existingWeek.setWeekNumber(trainingWeek.getWeekNumber());
                    existingWeek.setUpdatedAt(LocalDateTime.now());
                    // Update other fields as necessary
                    return trainingWeekRepository.save(existingWeek);
                })
                .orElseThrow(() -> new IllegalArgumentException("Training week not found with id: " + weekId + " in training block: " + trainingBlockId));
    }

    @Transactional
    @Override
    public void deleteTrainingWeek(UUID trainingBlockId, UUID weekId) {
        trainingWeekRepository.deleteByIdAndTrainingBlockId(weekId, trainingBlockId);
    }

    @Override
    public Optional<TrainingWeek> getTrainingWeekById(UUID trainingBlockId, UUID trainingWeekId) {
        return trainingWeekRepository.findByIdAndTrainingBlockId(trainingWeekId, trainingBlockId);
    }
}
