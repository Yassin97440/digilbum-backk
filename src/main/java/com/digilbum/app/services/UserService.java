package com.digilbum.app.services;

import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserService {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    @GetMapping("/all/{id}")
    public @ResponseBody Optional<User> getUserbyId(@PathVariable int id) {
        return userRepo.findById(id);
    }

    @PostMapping("/new")
    public @ResponseBody String newUser(@RequestBody User newUser){
        userRepo.save(newUser);
        return "";
    }
}

