package com.arismendy.powertracker.repositories;

import com.arismendy.powertracker.entities.TrainingBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingBlockRepository extends JpaRepository<TrainingBlock, UUID> {

}
