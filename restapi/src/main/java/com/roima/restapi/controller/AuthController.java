package com.roima.restapi.controller;


import com.roima.restapi.dto.request.LoginRequestDto;
import com.roima.restapi.dto.response.LoginResponseDto;
import com.roima.restapi.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request, HttpServletResponse response) {

        if ("admin".equals(request.getUsername())
                && "admin123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600); // 1 hour
            cookie.setSecure(true); // use HTTPS in production!
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            LoginResponseDto loginResponseDto = new LoginResponseDto(token);

            return  ResponseEntity.ok(loginResponseDto);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
