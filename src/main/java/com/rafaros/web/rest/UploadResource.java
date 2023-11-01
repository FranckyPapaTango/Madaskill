package com.rafaros.web.rest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/happy")
public class UploadResource {

    @Value("${file.base-path}")
    private String storagePath;

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            Path imagePath = Paths.get(storagePath).resolve(originalFilename);
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("../../../content/productsImages/" + originalFilename);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.  TRACE is : " + e);
        }
    }

    @PutMapping(value = "/update-image/{imageName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable String imageName, @RequestParam("file") MultipartFile file) {
        try {
            // Assurez-vous que l'image que vous souhaitez mettre à jour existe dans le stockage
            Path imagePath = Paths.get(storagePath).resolve(imageName);

            // Vérifiez si le fichier existe, s'il n'existe pas, vous pouvez renvoyer une réponse d'erreur appropriée.
            if (Files.exists(imagePath)) {
                // Obtenez le nom du nouveau fichier
                // String newImageName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
                String originalFilename = file.getOriginalFilename();
                Path newImagePath = Paths.get(storagePath).resolve(originalFilename);

                // Effectuez la mise à jour de l'image en renommant le fichier
                // Path newImagePath = Paths.get(storagePath).resolve(newImageName);
                Files.copy(file.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);

                // Supprimez l'ancien fichier
                Files.delete(imagePath);

                // Retournez le chemin du nouveau fichier
                return ResponseEntity.ok("../../../content/productsImages/" + originalFilename);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating image. TRACE is : " + e);
        }
    }

    @DeleteMapping("/delete-image/{imageName}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageName) {
        try {
            // Assurez-vous que l'image que vous souhaitez supprimer existe dans le stockage
            Path imagePath = Paths.get(storagePath).resolve(imageName);

            // Vérifiez si le fichier existe, s'il n'existe pas, vous pouvez renvoyer une réponse d'erreur appropriée.
            if (Files.exists(imagePath)) {
                Files.delete(imagePath); // Supprimez le fichier image
                return ResponseEntity.ok("Image deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image. TRACE is: " + e);
        }
    }
}
