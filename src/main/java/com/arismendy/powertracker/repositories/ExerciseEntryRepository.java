package com.arismendy.powertracker.repositories;

import com.arismendy.powertracker.entities.ExerciseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseEntryRepository extends JpaRepository<ExerciseEntry, UUID> {

    // Custom query methods can be added here if needed
    // For example, to find all entries by training day ID:
     List<ExerciseEntry> findAllByTrainingDayId(UUID trainingDayId);
}
