package com.arismendy.powertracker.controller;

import com.arismendy.powertracker.service.SyncService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/powertracker/v1/sync")
public class SpreadSheetSyncController {

    private final SyncService syncService;

    public SpreadSheetSyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @PostMapping(value = "/manualSync")
    public ResponseEntity<?> manualSync(){
        syncService.syncFromSheet();
        return ResponseEntity.ok("Synced Successfully");
    }

}
