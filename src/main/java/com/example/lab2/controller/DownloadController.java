package com.example.lab2.controller;


import com.example.lab2.service.WordService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class DownloadController {
    private final WordService wordService;
    @GetMapping
    public ResponseEntity<String> getFile(HttpServletResponse response) {
        return ResponseEntity.ok().body(wordService.downloadToWord(response));
    }
}
