package com.rafaros.service;

import com.rafaros.config.DataSourceConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class BackupService {

    /* déclarez un logger dans votre service : */

    private static final Logger logger = LoggerFactory.getLogger(BackupService.class);

    /**
     * GESTION DE LA MISE A JOUR A OU DE LA cREATION DU SCRIPT BACKUP
     */
    @Autowired
    private DataSourceConfig dataSourceConfig;

    public ResponseEntity<String> createBackup() {
        // Récupération des informations de la base de données à partir du fichier de configuration
        String pgUser = dataSourceConfig.getUsername();
        String pgPassword = dataSourceConfig.getPassword();
        String databaseName = dataSourceConfig.getUrl().split("/")[dataSourceConfig.getUrl().split("/").length - 1];
        String host = dataSourceConfig.getUrl().split("/")[2].split(":")[0];
        int port = Integer.parseInt(dataSourceConfig.getUrl().split(":")[3].split("/")[0]);

        String backupFilePath = "MadaskillBackup.sql";

        // Détecter le système d'exploitation
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;

        // Construire la commande pg_dump en fonction du système d'exploitation
        if (os.contains("win")) {
            System.out.println("===================================os.contains(\"win\")==================================");
            // Commande pour Windows
            String command = String.format("pg_dump -U %s -h %s -p %d -d %s -f %s", pgUser, host, port, databaseName, backupFilePath);
            // processBuilder = new ProcessBuilder("cmd.exe", "/c", "set PGPASSWORD=" + pgPassword + " && " + command);
            processBuilder = new ProcessBuilder("cmd.exe", "/c", command + "-W");
        } else {
            // Commande pour Linux/Unix/MacOS
            String command = String.format(
                "PGPASSWORD=%s pg_dump -U %s -h %s -p %d -d %s -f %s",
                pgPassword,
                pgUser,
                host,
                port,
                databaseName,
                backupFilePath
            );
            processBuilder = new ProcessBuilder("sh", "-c", command);
        }

        try {
            Process process = processBuilder.start();

            // Capturer la sortie de la commande
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            FileWriter fileWriter = new FileWriter(backupFilePath);
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.write(line + "\n");
            }

            // Fermer le FileWriter et le BufferedReader
            fileWriter.close();
            reader.close();

            // Attendre que le processus se termine
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                return ResponseEntity.ok("Backup créé avec succès");
            } else {
                // Capturer les éventuelles erreurs
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                StringBuilder errorMessage = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorMessage.append(line).append("\n");
                }
                errorReader.close();

                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de la sauvegarde : " + errorMessage.toString());
            }
        } catch (Exception e) {
            // Journaliser l'exception
            e.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * GESTION DE LA MISE A JOUR A PARTIR DU SCRIPT BACKUP
     */

    @Value("${file.backup-script-path}")
    private String backupScriptPath;

    public ResponseEntity<String> updateDatabase() {
        String pgUser = dataSourceConfig.getUsername();
        String pgPassword = dataSourceConfig.getPassword();
        String databaseName = dataSourceConfig.getUrl().split("/")[dataSourceConfig.getUrl().split("/").length - 1];
        String host = dataSourceConfig.getUrl().split("/")[2].split(":")[0];
        int port = Integer.parseInt(dataSourceConfig.getUrl().split(":")[3].split("/")[0]);

        String backupFilePath = "MadaskillBackup.backup";

        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;

        if (os.contains("win")) {
            System.out.println("===================================os.contains(\"win\")==================================");
            String command = String.format(
                "pg_restore -U %s -h %s -p %d -d %s -c -F c %s",
                pgUser,
                host,
                port,
                databaseName,
                backupFilePath
            );
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "set PGPASSWORD=" + pgPassword + " && " + command);
        } else {
            String command = String.format(
                "PGPASSWORD=%s pg_restore -U %s -h %s -p %d -d %s -c -F c %s",
                pgPassword,
                pgUser,
                host,
                port,
                databaseName,
                backupFilePath
            );
            processBuilder = new ProcessBuilder("sh", "-c", command);
        }

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();

            int processComplete = process.waitFor();
            if (processComplete == 0) {
                return ResponseEntity.ok("Restauration réussie avec succès");
            } else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                StringBuilder errorMessage = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorMessage.append(line).append("\n");
                }
                errorReader.close();
                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la restauration : " + errorMessage.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la restauration : " + e.getMessage());
        }
    }
}
