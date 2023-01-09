package com.example.lab2.controller;

import com.example.lab2.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
class UploadController {
    private final UploadService uploadService;
    @PostMapping()
    public ResponseEntity<String> uploadExcel(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok().body(uploadService.uploadFromExcel(file));
    }
}
