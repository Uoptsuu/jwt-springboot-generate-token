package com.spring.jwt.controller;

import com.spring.jwt.auth.AuthRequest;
import com.spring.jwt.auth.AuthResponse;
import com.spring.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AuthService authService;
    @GetMapping("/demo")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Success");
    }
}
