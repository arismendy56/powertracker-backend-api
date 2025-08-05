package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.TrainingDayDto;
import com.arismendy.powertracker.entities.TrainingDay;
import com.arismendy.powertracker.mapper.TrainingDayMapper;
import com.arismendy.powertracker.service.TrainingDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/weeks/{training-week-id}/days")
public class TrainingDayController {

    private final TrainingDayService trainingDayService;
    private final TrainingDayMapper trainingDayMapper;

    public TrainingDayController(TrainingDayService trainingDayService, TrainingDayMapper trainingDayMapper) {
        this.trainingDayService = trainingDayService;
        this.trainingDayMapper = trainingDayMapper;
    }

    @RequestMapping
    public ResponseEntity<ApiResponse<List<TrainingDayDto>>>getTrainingDays(@PathVariable("training-week-id") UUID trainingWeekId) {
        List<TrainingDay> trainingDays = trainingDayService.getTrainingDays(trainingWeekId);
        List<TrainingDayDto> trainingDayDtos = trainingDays.stream()
                .map(trainingDayMapper::toDto)
                .toList();

        ApiResponse<List<TrainingDayDto>> response = new ApiResponse<>(200,trainingDayDtos, "Training days retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingDayDto>> createTrainingDay(@PathVariable("training-week-id") UUID trainingWeekId,
                                                       @RequestBody TrainingDayDto trainingDayDto) {
        TrainingDay createdTrainingDay = trainingDayService.createTrainingDay(trainingWeekId, trainingDayMapper.fromDto(trainingDayDto));
        TrainingDayDto responseDto = trainingDayMapper.toDto(createdTrainingDay);
        ApiResponse<TrainingDayDto> apiResponse = new ApiResponse<>(200, responseDto, "Training day created successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/{training-day-id}")
    public ResponseEntity<ApiResponse<TrainingDayDto>> getTrainingDay(@PathVariable("training-week-id") UUID trainingWeekId,
                                                                     @PathVariable("training-day-id") UUID trainingDayId) {
        Optional<TrainingDay> trainingDay = trainingDayService.getTrainingDayById(trainingWeekId, trainingDayId);
        TrainingDayDto responseDto = trainingDayMapper.toDto(trainingDay.orElseThrow(() -> new IllegalArgumentException("Training day not found with id: " + trainingDayId)));
        ApiResponse<TrainingDayDto> apiResponse = new ApiResponse<>(200, responseDto, "Training day retrieved successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "/{training-day-id}")
    public ResponseEntity<ApiResponse<TrainingDayDto>> updateTrainingDay(@PathVariable("training-week-id") UUID trainingWeekId,
                                                                         @PathVariable("training-day-id") UUID trainingDayId,
                                                                         @RequestBody TrainingDayDto trainingDayDto) {
        // Implementation for updating a training day would go here
        TrainingDay updatedTrainingDay = trainingDayService.updateTrainingDay(trainingWeekId, trainingDayId, trainingDayMapper.fromDto(trainingDayDto));
        TrainingDayDto responseDto = trainingDayMapper.toDto(updatedTrainingDay);
        ApiResponse<TrainingDayDto> apiResponse = new ApiResponse<>(200, responseDto, "Training day updated successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{training-day-id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainingDay(@PathVariable("training-week-id") UUID trainingWeekId,
                                                               @PathVariable("training-day-id") UUID trainingDayId) {
        // Implementation for deleting a training day would go here
        trainingDayService.deleteTrainingDay(trainingWeekId, trainingDayId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(200, null, "Training day deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
