package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.TrainingDayDto;
import com.arismendy.powertracker.entities.TrainingDay;
import com.arismendy.powertracker.mapper.TrainingDayMapper;
import com.arismendy.powertracker.service.TrainingDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
                                                        TrainingDayDto trainingDayDto) {
        TrainingDay createdTrainingDay = trainingDayService.createTrainingDay(trainingWeekId, trainingDayMapper.fromDto(trainingDayDto));
        TrainingDayDto responseDto = trainingDayMapper.toDto(createdTrainingDay);
        ApiResponse<TrainingDayDto> apiResponse = new ApiResponse<>(200, responseDto, "Training day created successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
