package com.spring.jwt.controller;

import com.spring.jwt.entity.User;
import com.spring.jwt.model.DTO.UserDTO;
import com.spring.jwt.service.AuthService;
import com.spring.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    @GetMapping("/home")
    public ResponseEntity<List> homePage(){
        List<User> userList = userService.getAllUser();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO().toUserDTO(user));
        }
        return ResponseEntity.ok(userDTOList);
    }
}
