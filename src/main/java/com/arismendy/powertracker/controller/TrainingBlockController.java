package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.TrainingBlockDto;
import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.mapper.TrainingBlockMapper;
import com.arismendy.powertracker.service.TrainingBlockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ApiResponse<List<TrainingBlockDto>>>getTrainingBlocks() {
        List<TrainingBlock> trainingBlocks = trainingBlockService.getAllTrainingBlocks();
        if(trainingBlocks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<TrainingBlockDto> trainingBlockDtos = trainingBlocks.stream()
                .map(trainingBlockMapper::toDto)
                .toList();
        ApiResponse<List<TrainingBlockDto>> apiResponse = new ApiResponse<>(200, trainingBlockDtos, "Training blocks retrieved successfully");
        return ResponseEntity.ok(apiResponse);
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
