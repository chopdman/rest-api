package com.roima.restapi.controller;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/api/v1/cookie")
    public String sendCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("employee-app", "active");
        response.addCookie(cookie);
        return "Cookie sent";
    }
}
