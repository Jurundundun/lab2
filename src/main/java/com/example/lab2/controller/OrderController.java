package com.example.lab2.controller;


import com.example.lab2.entity.OrderEntity;
import com.example.lab2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public Iterable<OrderEntity> getAll() {
        return orderService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(orderService.getById(id));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody OrderEntity order){
        return ResponseEntity.ok().body(orderService.save(order));
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody OrderEntity order) {
        return ResponseEntity.ok().body(orderService.delete(order));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(orderService.deleteById(id));
    }
    @GetMapping("/condition")
    public List<OrderEntity> getByCondition(){
        return orderService.ordersByCondition();
    }
}
