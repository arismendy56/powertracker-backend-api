package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.TrainingBlockDto;
import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.mapper.TrainingBlockMapper;
import com.arismendy.powertracker.service.TrainingBlockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/training-blocks")
public class TrainingBlockController {

    private final TrainingBlockService trainingBlockService;
    private final TrainingBlockMapper trainingBlockMapper;
    public TrainingBlockController(TrainingBlockService trainingBlockService, TrainingBlockMapper trainingBlockMapper) {
        this.trainingBlockService = trainingBlockService;
        this.trainingBlockMapper = trainingBlockMapper;
    }

    @GetMapping
    public ResponseEntity<?> getTrainingBlocks() {
        return ResponseEntity.of(
                Optional.ofNullable(trainingBlockService.getAllTrainingBlocks(
                )));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingBlockDto>> createTrainingBlock(@Valid @RequestBody TrainingBlockDto trainingBlockDto) {
        TrainingBlock createdTrainingBlock = trainingBlockService.createTrainingBlock(
                trainingBlockMapper.fromDto(trainingBlockDto));
        TrainingBlockDto responseDto = trainingBlockMapper.toDto(createdTrainingBlock);
        ApiResponse<TrainingBlockDto> apiResponse = new ApiResponse<>(200, responseDto,"Training block created successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
