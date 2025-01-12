package com.digilbum.app.controller;

import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import com.digilbum.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

