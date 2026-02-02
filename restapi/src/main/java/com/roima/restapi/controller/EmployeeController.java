package com.roima.restapi.controller;


import com.roima.restapi.dto.request.EmployeeRequestDto;
import com.roima.restapi.dto.response.EmployeeResponseDto;
import com.roima.restapi.service.EmployeeService;
import com.roima.restapi.validation.OnCreate;
import com.roima.restapi.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(
            @Validated({OnCreate.class, Default.class})
            @RequestBody EmployeeRequestDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> update(
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping(value = "/{id}/department",produces = "application/vnd.roima.v1+json")
    public ResponseEntity<EmployeeResponseDto> updateDepartment(
            @PathVariable Long id,
            @RequestParam String department) {
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setDepartment(department);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}",headers = "X-API-VERSION=1")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Employee deleted successfully");
    }
}

