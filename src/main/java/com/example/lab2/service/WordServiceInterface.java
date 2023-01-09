package com.example.lab2.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface WordServiceInterface {
    public String downloadToWord(HttpServletResponse response) throws IOException;
}
