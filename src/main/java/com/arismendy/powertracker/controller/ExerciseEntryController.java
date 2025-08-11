package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.ApiResponse;
import com.arismendy.powertracker.dto.ExerciseEntryDto;
import com.arismendy.powertracker.mapper.ExerciseEntryMapper;
import com.arismendy.powertracker.service.ExerciseEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/days/{training-day-id}/exercises")
public class ExerciseEntryController {

    private final ExerciseEntryService exerciseEntryService;
    private final ExerciseEntryMapper exerciseEntryMapper;

    public ExerciseEntryController(ExerciseEntryService exerciseEntryService, ExerciseEntryMapper exerciseEntryMapper) {
        this.exerciseEntryService = exerciseEntryService;
        this.exerciseEntryMapper = exerciseEntryMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExerciseEntryDto>>> getAllExcercisesByTrainingDayId(
            @PathVariable("training-day-id") UUID trainingDayId) {
        List<ExerciseEntryDto> exerciseEntries = exerciseEntryService.getAllByTrainingDayId(trainingDayId)
                .stream()
                .map(exerciseEntryMapper::toDto)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse<>(200, exerciseEntries, "Exercise entries retrieved successfully")
        );
    }

}
