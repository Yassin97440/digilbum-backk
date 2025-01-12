package com.digilbum.app.controller;

import com.digilbum.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/isEmailExist")
    public boolean isEmailExist(@RequestBody String email) {
        return userService.isEmailExists(email);
    }
}
