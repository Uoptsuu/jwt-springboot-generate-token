package com.spring.jwt.controller;

import com.spring.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    @GetMapping("/home")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Hello");
    }
}
