package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.TrainingWeekDto;
import com.arismendy.powertracker.entities.TrainingWeek;
import com.arismendy.powertracker.mapper.TrainingWeekMapper;
import com.arismendy.powertracker.service.TrainingWeekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/training-blocks/{training-block-id}/weeks")
public class TrainingWeekController {

    private final TrainingWeekService trainingWeekService;
    private final TrainingWeekMapper trainingWeekMapper;

    public TrainingWeekController(TrainingWeekService trainingWeekService, TrainingWeekMapper trainingWeekMapper) {
        this.trainingWeekService = trainingWeekService;
        this.trainingWeekMapper = trainingWeekMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TrainingWeekDto>>> getTrainingWeeks(@PathVariable("training-block-id") UUID trainingBlockId) {
        List<TrainingWeek> trainingWeeks = trainingWeekService.getTrainingWeeks(trainingBlockId);
        if (trainingWeeks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<TrainingWeekDto> trainingWeekDtos = trainingWeeks.stream()
                .map(trainingWeekMapper::toDto)
                .toList();
        ApiResponse<List<TrainingWeekDto>> apiResponse = new ApiResponse<>(200, trainingWeekDtos, "Training weeks retrieved successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingWeekDto>> createTrainingWeek(@PathVariable("training-block-id") UUID trainingBlockId,
                                                               @RequestBody TrainingWeekDto trainingWeekDto) {
        TrainingWeek createdTrainingWeek = trainingWeekService.createTrainingWeek(trainingBlockId, trainingWeekMapper.fromDto(trainingWeekDto));
        TrainingWeekDto responseDto = trainingWeekMapper.toDto(createdTrainingWeek);
        ApiResponse<TrainingWeekDto> apiResponse = new ApiResponse<>(200, responseDto, "Training week created successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/{training-week-id}")
    public ResponseEntity<ApiResponse<TrainingWeekDto>> getTrainingWeek(@PathVariable("training-block-id") UUID trainingBlockId,
                                                                        @PathVariable("training-week-id") UUID trainingWeekId) {
        Optional<TrainingWeek> trainingWeek = trainingWeekService.getTrainingWeekById(trainingBlockId, trainingWeekId);
        TrainingWeekDto responseDto = trainingWeekMapper.toDto(trainingWeek.orElseThrow(() -> new IllegalArgumentException("Training week not found with id: " + trainingWeekId)));
        ApiResponse<TrainingWeekDto> apiResponse = new ApiResponse<>(200, responseDto, "Training week retrieved successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "/{training-week-id}")
    public ResponseEntity<ApiResponse<TrainingWeekDto>> updateTrainingWeek(@PathVariable("training-block-id") UUID trainingBlockId,
                                                                           @PathVariable("training-week-id") UUID trainingWeekId,
                                                                           @RequestBody TrainingWeekDto trainingWeekDto) {
        TrainingWeek updatedTrainingWeek = trainingWeekService.updateTrainingWeek(trainingBlockId, trainingWeekId, trainingWeekMapper.fromDto(trainingWeekDto));
        TrainingWeekDto responseDto = trainingWeekMapper.toDto(updatedTrainingWeek);
        ApiResponse<TrainingWeekDto> apiResponse = new ApiResponse<>(200, responseDto, "Training week updated successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{training-week-id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainingWeek(@PathVariable("training-block-id") UUID trainingBlockId,
                                                                @PathVariable("training-week-id") UUID trainingWeekId) {
        trainingWeekService.deleteTrainingWeek(trainingBlockId, trainingWeekId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(200, null, "Training week deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
