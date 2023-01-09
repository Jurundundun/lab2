package com.example.lab2.controller;

import com.example.lab2.entity.ProviderEntity;
import com.example.lab2.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;
    @GetMapping("/all")
    public Iterable<ProviderEntity> getAll() {
        return providerService.getAll();
    }
    @GetMapping
    public ResponseEntity<ProviderEntity> getById(@RequestParam Integer id){
        return ResponseEntity.ok().body(providerService.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProviderEntity provider){

        return ResponseEntity.ok().body(providerService.save(provider));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(providerService.deleteById(id));
    }
}
