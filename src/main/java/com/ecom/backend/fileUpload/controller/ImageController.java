package com.ecom.backend.fileUpload.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
public class ImageController {

    @GetMapping("/images/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        String imagePath = "src/products/images/" + imageName;
        File imageFile = new File(imagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageFile.length());

        try (InputStream inputStream = new FileInputStream(imageFile)) {
            byte[] imageBytes = inputStream.readAllBytes();
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
    }
}
