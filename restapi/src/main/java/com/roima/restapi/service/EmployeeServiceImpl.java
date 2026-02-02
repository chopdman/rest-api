package com.roima.restapi.service;


import com.roima.restapi.dto.request.EmployeeRequestDto;
import com.roima.restapi.dto.response.EmployeeResponseDto;
import com.roima.restapi.entity.Employee;
import com.roima.restapi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto dto) {
        Employee employee = mapper.map(dto, Employee.class);
        return mapper.map(repository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto getById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return mapper.map(employee, EmployeeResponseDto.class);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeResponseDto.class))
                .toList();
    }

    @Override
    public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        mapper.map(dto, employee);
        return mapper.map(repository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
