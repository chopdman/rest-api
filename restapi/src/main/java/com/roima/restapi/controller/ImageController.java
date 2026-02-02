package com.roima.restapi.controller;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/employees")
public class ImageController {

    private static final String IMAGE_DIR = "src/main/resources/images/";

    @PostMapping("/{id}/image")
    @CacheEvict(value = "images", key = "#id")
    public void uploadImage(@PathVariable Long id,
                            @RequestParam MultipartFile file) throws IOException {
        Files.write(Path.of(IMAGE_DIR + id + ".png"), file.getBytes());
    }

    @GetMapping("/{id}/image")
    @Cacheable(value = "images", key = "#id")
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_DIR + id + ".png"));
    }
}
