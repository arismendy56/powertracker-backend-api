package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.repositories.TrainingBlockRepository;
import com.arismendy.powertracker.service.TrainingBlockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

}
