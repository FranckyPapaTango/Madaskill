package com.rafaros.web.rest;

import com.rafaros.service.BackupService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BackupResource {

    @Autowired
    private BackupService backupService;

    @PostMapping("/create-backup")
    public ResponseEntity<String> createBackup() {
        return backupService.createBackup();
    }

    @PostMapping("/update-database")
    public ResponseEntity<String> updateDatabase() {
        try {
            backupService.updateDatabase();
            return ResponseEntity.ok("Database updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating database: " + e.getMessage());
        }
    }
}
