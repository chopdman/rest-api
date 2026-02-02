package com.roima.restapi.controller;


import com.roima.restapi.dto.request.LoginRequestDto;
import com.roima.restapi.dto.response.LoginResponseDto;
import com.roima.restapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {

        if ("admin".equals(request.getUsername())
                && "admin123".equals(request.getPassword())) {

            return new LoginResponseDto(jwtUtil.generateToken(request.getUsername()));
        }

        throw new RuntimeException("Invalid credentials");
    }
}
