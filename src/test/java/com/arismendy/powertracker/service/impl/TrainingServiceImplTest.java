package com.arismendy.powertracker.service.impl;

import com.arismendy.powertracker.entities.TrainingBlock;
import com.arismendy.powertracker.repositories.TrainingBlockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

    @Mock
    private TrainingBlockRepository trainingBlockRepository;
    @InjectMocks
    private TrainingBlockServiceImpl trainingBlockService;

    @Test
    void testGetAllTrainingBlocksReturnsList() {
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockRepository.findAll()).thenReturn(List.of(block));

        List<TrainingBlock> result = trainingBlockService.getAllTrainingBlocks();

        assertEquals(1, result.size());
        verify(trainingBlockRepository, times(1)).findAll();
    }

    @Test
    void testCreateTrainingBlockThrowsIfIdPresent() {
        TrainingBlock block = new TrainingBlock();
        block.setId(UUID.randomUUID());

        assertThrows(IllegalArgumentException.class, () -> trainingBlockService.createTrainingBlock(block));
        verify(trainingBlockRepository, never()).save(any());
    }

    @Test
    void testCreateTrainingBlockSavesNewBlock() {
        TrainingBlock block = new TrainingBlock();
        block.setName("Block 1");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now().plusDays(7));
        block.setNotes("Notes");

        TrainingBlock savedBlock = new TrainingBlock();
        when(trainingBlockRepository.save(any())).thenReturn(savedBlock);

        TrainingBlock result = trainingBlockService.createTrainingBlock(block);

        assertNotNull(result);
        ArgumentCaptor<TrainingBlock> captor = ArgumentCaptor.forClass(TrainingBlock.class);
        verify(trainingBlockRepository).save(captor.capture());
        assertNull(captor.getValue().getId());
        assertEquals("Block 1", captor.getValue().getName());
        assertNotNull(captor.getValue().getCreatedAt());
        assertNotNull(captor.getValue().getUpdatedAt());
    }

    @Test
    void testGetTrainingBlockByIdReturnsOptional() {
        UUID id = UUID.randomUUID();
        TrainingBlock block = new TrainingBlock();
        when(trainingBlockRepository.findById(id)).thenReturn(Optional.of(block));

        Optional<TrainingBlock> result = trainingBlockService.getTrainingBlockById(id);

        assertTrue(result.isPresent());
        verify(trainingBlockRepository).findById(id);
    }

    @Test
    void testUpdateTrainingBlockUpdatesFields() {
        UUID id = UUID.randomUUID();
        TrainingBlock existing = new TrainingBlock();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setStartDate(LocalDate.now());
        existing.setEndDate(LocalDate.now().plusDays(5));
        existing.setNotes("Old Notes");

        TrainingBlock update = new TrainingBlock();
        update.setName("New Name");
        update.setStartDate(LocalDate.now().plusDays(1));
        update.setEndDate(LocalDate.now().plusDays(10));
        update.setNotes("New Notes");

        when(trainingBlockRepository.findById(id)).thenReturn(Optional.of(existing));
        when(trainingBlockRepository.save(any())).thenReturn(existing);

        TrainingBlock result = trainingBlockService.updateTrainingBlock(id, update);

        assertEquals("New Name", result.getName());
        assertEquals("New Notes", result.getNotes());
        assertEquals(update.getStartDate(), result.getStartDate());
        assertEquals(update.getEndDate(), result.getEndDate());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void testUpdateTrainingBlockThrowsIfNotFound() {
        UUID id = UUID.randomUUID();
        TrainingBlock update = new TrainingBlock();
        when(trainingBlockRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> trainingBlockService.updateTrainingBlock(id, update));
    }

    @Test
    void testDeleteTrainingBlockCallsRepository() {
        UUID id = UUID.randomUUID();

        trainingBlockService.deleteTrainingBlock(id);

        verify(trainingBlockRepository, times(1)).deleteById(id);
    }
}