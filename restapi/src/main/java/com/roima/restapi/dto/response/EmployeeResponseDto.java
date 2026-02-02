package com.roima.restapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String email;
    private String department;
}
