package com.arismendy.powertracker.repositories;

import com.arismendy.powertracker.entities.TrainingWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingWeekRepository extends JpaRepository<TrainingWeek, UUID> {

    List<TrainingWeek> findByTrainingBlockId(UUID trainingBlockId);
    Optional<TrainingWeek> findByIdAndTrainingBlockId(UUID id, UUID trainingBlockId);
    void deleteByIdAndTrainingBlockId(UUID id, UUID trainingBlockId);
}
