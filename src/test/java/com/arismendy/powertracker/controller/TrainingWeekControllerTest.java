package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.TrainingWeekDto;
import com.arismendy.powertracker.entities.TrainingWeek;
import com.arismendy.powertracker.mapper.TrainingWeekMapper;
import com.arismendy.powertracker.service.TrainingWeekService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TrainingWeekControllerTest {

    @Mock
    private TrainingWeekService trainingWeekService;
    @Mock
    private TrainingWeekMapper trainingWeekMapper;
    @InjectMocks
    private TrainingWeekController trainingWeekController;
    private MockMvc mockMvc;

    private final UUID blockId = UUID.randomUUID();
    private final UUID weekId = UUID.randomUUID();
    private TrainingWeek trainingWeek;
    private final TrainingWeekDto trainingWeekDto = new TrainingWeekDto(UUID.randomUUID(), UUID.randomUUID(), 1, null, null, null);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainingWeekController).build();
        trainingWeek = new TrainingWeek();
    }

    @Test
    void getTrainingWeeks_returnsList() throws Exception {
        List<TrainingWeek> weeks = List.of(trainingWeek);
        when(trainingWeekService.getTrainingWeeks(blockId)).thenReturn(weeks);
        when(trainingWeekMapper.toDto(any())).thenReturn(trainingWeekDto);

        mockMvc.perform(get("/api/v1/training-blocks/" + blockId + "/weeks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.message", is("Training weeks retrieved successfully")));
    }

    @Test
    void getTrainingWeeks_returnsNoContent() throws Exception {
        when(trainingWeekService.getTrainingWeeks(blockId)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/training-blocks/" + blockId + "/weeks"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createTrainingWeek_returnsCreatedWeek() throws Exception {
        when(trainingWeekMapper.fromDto(any())).thenReturn(trainingWeek);
        when(trainingWeekService.createTrainingWeek(eq(blockId), any())).thenReturn(trainingWeek);
        when(trainingWeekMapper.toDto(any())).thenReturn(trainingWeekDto);

        mockMvc.perform(post("/api/v1/training-blocks/" + blockId + "/weeks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Training week created successfully")));
    }

    @Test
    void getTrainingWeek_returnsWeek() throws Exception {
        when(trainingWeekService.getTrainingWeekById(blockId, weekId)).thenReturn(Optional.of(trainingWeek));
        when(trainingWeekMapper.toDto(any())).thenReturn(trainingWeekDto);

        mockMvc.perform(get("/api/v1/training-blocks/" + blockId + "/weeks/" + weekId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Training week retrieved successfully")));
    }

    @Test
    void getTrainingWeek_notFound_throwsException() throws Exception {
        when(trainingWeekService.getTrainingWeekById(blockId, weekId)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/training-blocks/" + blockId + "/weeks/" + weekId))
                .andExpect(status().isInternalServerError()); // Exception thrown, handled by Spring
    }

    @Test
    void updateTrainingWeek_returnsUpdatedWeek() throws Exception {
        when(trainingWeekMapper.fromDto(any())).thenReturn(trainingWeek);
        when(trainingWeekService.updateTrainingWeek(eq(blockId), eq(weekId), any())).thenReturn(trainingWeek);
        when(trainingWeekMapper.toDto(any())).thenReturn(trainingWeekDto);

        mockMvc.perform(put("/api/v1/training-blocks/" + blockId + "/weeks/" + weekId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Training week updated successfully")));
    }

    @Test
    void deleteTrainingWeek_returnsSuccess() throws Exception {
        doNothing().when(trainingWeekService).deleteTrainingWeek(blockId, weekId);
        mockMvc.perform(delete("/api/v1/training-blocks/" + blockId + "/weeks/" + weekId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Training week deleted successfully")));
    }
}

