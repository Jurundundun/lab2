package com.example.lab2.controller;


import com.example.lab2.entity.PositionEntity;
import com.example.lab2.model.PositionModel;
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
    @GetMapping()
    public Iterable<PositionModel> getAll() {
        return positionService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PositionModel> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(positionService.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody PositionModel position){
        return ResponseEntity.ok().body(positionService.saveEntity(
                PositionModel.modelToPosition(position, providerService, orderService)));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteEntity(@RequestBody PositionEntity position) {
        return ResponseEntity.ok().body(positionService.deleteEntity(position));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(positionService.deleteById(id));
    }
}
