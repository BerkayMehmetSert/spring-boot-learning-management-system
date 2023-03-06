package com.bms.learningmanagementsystem.controller;

import com.bms.learningmanagementsystem.dto.CycleDto;
import com.bms.learningmanagementsystem.service.CycleService;
import com.bms.learningmanagementsystem.service.request.CreateCycleRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCycleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cycles")
public class CycleController {
    private final CycleService service;

    public CycleController(CycleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCycle(@RequestBody CreateCycleRequest request) {
        service.createCycle(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCycle(@PathVariable String id, @RequestBody UpdateCycleRequest request) {
        service.updateCycle(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCycle(@PathVariable String id) {
        service.deleteCycle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CycleDto> getCycleById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCycleById(id));
    }

    @GetMapping
    public ResponseEntity<List<CycleDto>> getAllCycles() {
        return ResponseEntity.ok(service.getAllCycles());
    }
}
