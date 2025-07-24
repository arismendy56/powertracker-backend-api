package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingDay;
import com.arismendy.powertracker.repositories.TrainingDayRepository;
import com.arismendy.powertracker.repositories.TrainingWeekRepository;
import com.arismendy.powertracker.service.TrainingDayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainingDayServiceImpl implements TrainingDayService {

    private final TrainingDayRepository trainingDayRepository;
    private final TrainingWeekRepository trainingWeekRepository;

    public TrainingDayServiceImpl(TrainingDayRepository trainingDayRepository, TrainingWeekRepository trainingWeekRepository) {
        this.trainingDayRepository = trainingDayRepository;
        this.trainingWeekRepository = trainingWeekRepository;
    }

    @Override
    public List<TrainingDay> getTrainingDays(UUID trainingWeekId) {
        return trainingDayRepository.findAllByWeekId(trainingWeekId);
    }

    @Override
    public TrainingDay createTrainingDay(UUID trainingWeekId, TrainingDay trainingDay) {
        if(trainingWeekId == null || trainingDay == null) {
            throw new IllegalArgumentException("Training week ID and training day cannot be null");
        }
        return trainingWeekRepository.findById(trainingWeekId)
                .map(trainingWeek -> {
                    trainingDay.setWeek(trainingWeek);
                    return trainingDayRepository.save(trainingDay);
                })
                .orElseThrow(() -> new IllegalArgumentException("Training week not found with id: " + trainingWeekId));
    }


}
