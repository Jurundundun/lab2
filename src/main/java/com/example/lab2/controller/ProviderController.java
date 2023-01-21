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
    @GetMapping()
    public Iterable<ProviderEntity> getAll() {
        return providerService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProviderEntity> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(providerService.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProviderEntity provider){

        return ResponseEntity.ok().body(providerService.save(provider));
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody ProviderEntity provider) {
        return ResponseEntity.ok().body(providerService.delete(provider));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(providerService.deleteById(id));
    }
}
