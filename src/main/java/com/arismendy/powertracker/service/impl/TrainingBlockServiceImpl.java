package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.repositories.TrainingBlockRepository;
import com.arismendy.powertracker.service.TrainingBlockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingBlockServiceImpl implements TrainingBlockService {

    private final TrainingBlockRepository trainingBlockRepository;

    public TrainingBlockServiceImpl(TrainingBlockRepository trainingBlockRepository) {
        this.trainingBlockRepository = trainingBlockRepository;
    }
    @Override
    public List<TrainingBlock> getAllTrainingBlocks() {
        return trainingBlockRepository.findAll();
    }

    @Override
    public TrainingBlock createTrainingBlock(TrainingBlock trainingBlock) {

        if(null != trainingBlock.getId()) {
            throw new IllegalArgumentException("Training block already has an ID!");
        }

        LocalDateTime now = LocalDateTime.now();

        return trainingBlockRepository.save(new TrainingBlock(
                null,
                trainingBlock.getName(),
                trainingBlock.getStartDate(),
                trainingBlock.getEndDate(),
                trainingBlock.getNotes(),
                null,
                now,
                now,
                null
                ));
    }

    @Override
    public Optional<TrainingBlock> getTrainingBlockById(UUID id) {
        return trainingBlockRepository.findById(id);
    }

    @Override
    public TrainingBlock updateTrainingBlock(UUID id, TrainingBlock trainingBlock) {
        return trainingBlockRepository.findById(id)
                .map(existingBlock -> {
                    existingBlock.setName(trainingBlock.getName());
                    existingBlock.setStartDate(trainingBlock.getStartDate());
                    existingBlock.setEndDate(trainingBlock.getEndDate());
                    existingBlock.setNotes(trainingBlock.getNotes());
                    existingBlock.setUpdatedAt(LocalDateTime.now());
                    return trainingBlockRepository.save(existingBlock);
                })
                .orElseThrow(() -> new IllegalArgumentException("Training block with ID " + id + " not found"));
    }

    @Override
    public void deleteTrainingBlock(UUID id) {
        trainingBlockRepository.deleteById(id);
    }


}
