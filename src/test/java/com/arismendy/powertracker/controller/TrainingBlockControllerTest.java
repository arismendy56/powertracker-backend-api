package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.dto.TrainingBlockDto;
import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.mapper.TrainingBlockMapper;
import com.arismendy.powertracker.service.TrainingBlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class TrainingBlockControllerTest {

    @Mock
    private TrainingBlockService trainingBlockService;

    @Mock
    private TrainingBlockMapper trainingBlockMapper;

    @InjectMocks
    private TrainingBlockController trainingBlockController;

    private MockMvc mockMvc;

    static final TrainingBlockDto TEST_DTO = new TrainingBlockDto(UUID.randomUUID(), "Test Block", null, null, "Test notes", 1L, null, null, Collections.emptyList());
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainingBlockController).build();
    }

    @Test
    void testGetTrainingBlocksReturnsOk() throws Exception {
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockService.getAllTrainingBlocks()).thenReturn(List.of(block));
        when(trainingBlockMapper.toDto(block)).thenReturn(TEST_DTO);

        mockMvc.perform(get("/api/v1/training-blocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Training blocks retrieved successfully"));
    }

    @Test
    void testGetTrainingBlocksReturnsNoContent() throws Exception {
        when(trainingBlockService.getAllTrainingBlocks()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/training-blocks"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCreateTrainingBlockReturnsOk() throws Exception {
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockMapper.fromDto(any())).thenReturn(block);
        when(trainingBlockService.createTrainingBlock(block)).thenReturn(block);
        when(trainingBlockMapper.toDto(block)).thenReturn(TEST_DTO);

        mockMvc.perform(post("/api/v1/training-blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Block\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Training block created successfully"));
    }

    @Test
    void testGetTrainingBlockReturnsOk() throws Exception {
        UUID id = UUID.randomUUID();
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockService.getTrainingBlockById(id)).thenReturn(Optional.of(block));
        when(trainingBlockMapper.toDto(block)).thenReturn(TEST_DTO);

        mockMvc.perform(get("/api/v1/training-blocks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Training block retrieved successfully"));
    }

    @Test
    void testGetTrainingBlockReturnsNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(trainingBlockService.getTrainingBlockById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/training-blocks/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTrainingBlockReturnsOk() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/training-blocks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Training block deleted successfully"));
        verify(trainingBlockService, times(1)).deleteTrainingBlock(id);
    }

    @Test
    void testUpdateTrainingBlockReturnsOk() throws Exception {
        UUID id = UUID.randomUUID();
        TrainingBlockDto dto = new TrainingBlockDto(id, "Updated Block", null, null, "Updated notes", 1L, null, null, Collections.emptyList());
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockMapper.fromDto(dto)).thenReturn(block);
        when(trainingBlockService.updateTrainingBlock(eq(id), any())).thenReturn(block);
        when(trainingBlockMapper.toDto(block)).thenReturn(dto);

        mockMvc.perform(put("/api/v1/training-blocks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Block\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Training block updated successfully"));
    }
}