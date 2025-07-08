package com.arismendy.powertracker.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "spreadsheet_sync")
public class SpreadsheetSync {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String sheetId;

    @Column(nullable = false)
    private LocalDateTime lastSync;

    public SpreadsheetSync() {}

    public SpreadsheetSync(User user, String sheetId, LocalDateTime lastSync) {
        this.user = user;
        this.sheetId = sheetId;
        this.lastSync = lastSync;
    }

}

