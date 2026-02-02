package com.roima.restapi.controller;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/employees")
public class ImageController {

    private static final String IMAGE_DIR = "uploads";

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CacheEvict(value = "images", key = "#id")
    public ResponseEntity<String> uploadImage(@PathVariable Long id,
                                              @RequestParam MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(IMAGE_DIR);

        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(id + "-" + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        return ResponseEntity.status(HttpStatus.CREATED).body("Image Uploaded Successfully");
    }

    @GetMapping("/{id}/image")
    @Cacheable(value = "images", key = "#id")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        Path imagePath = Files.list(Paths.get(IMAGE_DIR)).filter(path -> path.getFileName().toString().startsWith(id+"-")).findFirst().orElseThrow(()->new RuntimeException("Image not found"));

        byte[] imageBytes = Files.readAllBytes(imagePath);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
