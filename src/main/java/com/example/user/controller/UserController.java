package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable String userId) throws Exception {
        return userService.getUser(userId);
    }

    @PostMapping("")
    public void setUser(@RequestBody User user) {
        userService.setUser(user);
    }
}
