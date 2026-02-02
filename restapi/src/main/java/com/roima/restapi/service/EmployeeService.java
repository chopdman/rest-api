package com.roima.restapi.service;



import com.roima.restapi.dto.request.EmployeeRequestDto;
import com.roima.restapi.dto.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto create(EmployeeRequestDto dto);
    EmployeeResponseDto getById(Long id);
    List<EmployeeResponseDto> getAll();
    EmployeeResponseDto update(Long id, EmployeeRequestDto dto);
    void delete(Long id);
}
