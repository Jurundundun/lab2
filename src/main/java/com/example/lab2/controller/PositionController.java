package com.example.lab2.controller;


import com.example.lab2.entity.PositionEntity;
import com.example.lab2.service.OrderService;
import com.example.lab2.service.PositionService;
import com.example.lab2.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    private final ProviderService providerService;
    private final OrderService orderService;
    @GetMapping("/all")
    public Iterable<PositionEntity> getAll() {
        return positionService.getAll();
    }
    @GetMapping
    public ResponseEntity<PositionEntity> getById(@RequestParam Integer id){
        return ResponseEntity.ok().body(positionService.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody PositionEntity position, @RequestParam Integer providerId, @RequestParam Integer orderId){
        position.setProvider(providerService.getById(providerId));
        position.setOrder(orderService.getById(orderId));
        return ResponseEntity.ok().body(positionService.save(position));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(positionService.deleteById(id));
    }
}
