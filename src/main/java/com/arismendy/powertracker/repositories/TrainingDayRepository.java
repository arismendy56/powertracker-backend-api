package com.arismendy.powertracker.repositories;

import com.arismendy.powertracker.entities.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, UUID> {

    // This interface will inherit all CRUD operations from JpaRepository
    // No additional methods are needed unless specific queries are required

    List<TrainingDay> findAllByWeekId(UUID weekId);
}
