package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingDay;
import com.arismendy.powertracker.repositories.TrainingDayRepository;
import com.arismendy.powertracker.repositories.TrainingWeekRepository;
import com.arismendy.powertracker.service.TrainingDayService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        LocalDateTime now = LocalDateTime.now();
        return trainingWeekRepository.findById(trainingWeekId)
                .map(trainingWeek -> {
                    trainingDay.setWeek(trainingWeek);
                    return trainingDayRepository.save(new TrainingDay(null,
                            trainingDay.getDayNumber(),
                            LocalDate.now(),
                            trainingDay.getWeek(),
                            now,
                            now,
                            null
                            ));
                })
                .orElseThrow(() -> new IllegalArgumentException("Training week not found with id: " + trainingWeekId));
    }

    @Override
    public TrainingDay updateTrainingDay(UUID trainingWeekId, UUID dayId, TrainingDay trainingDayDto) {
        return trainingDayRepository.findByIdAndWeekId(dayId, trainingWeekId)
                .map(existingDay -> {
                    existingDay.setDayNumber(trainingDayDto.getDayNumber());
                    existingDay.setUpdatedAt(LocalDateTime.now());
                    // Update other fields as necessary
                    return trainingDayRepository.save(existingDay);
                })
                .orElseThrow(() -> new IllegalArgumentException("Training day not found with id: " + dayId + " for week id: " + trainingWeekId));
    }

    @Override
    public void deleteTrainingDay(UUID trainingWeekId, UUID dayId) {
        trainingDayRepository.findByIdAndWeekId(dayId, trainingWeekId)
                .ifPresentOrElse(trainingDayRepository::delete,
                        () -> { throw new IllegalArgumentException("Training day not found with id: " + dayId + " for week id: " + trainingWeekId); });
    }

    @Override
    public Optional<TrainingDay> getTrainingDayById(UUID trainingWeekId, UUID trainingDayId) {

        if(trainingWeekId == null || trainingDayId == null) {
            throw new IllegalArgumentException("Training week ID and training day ID cannot be null");
        }

        return trainingDayRepository.findByIdAndWeekId(trainingDayId, trainingWeekId);
    }


}
