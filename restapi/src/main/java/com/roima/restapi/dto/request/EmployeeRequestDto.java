package com.roima.restapi.dto.request;


import com.roima.restapi.validation.OnCreate;
import com.roima.restapi.validation.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {

    @NotBlank(groups = OnCreate.class)
    private String name;

    @Email
    private String email;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String department;
}

