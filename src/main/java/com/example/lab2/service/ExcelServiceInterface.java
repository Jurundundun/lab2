package com.example.lab2.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelServiceInterface {
    String uploadFromExcel(MultipartFile file);
}
