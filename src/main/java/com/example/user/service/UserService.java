package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Optional<User> getUser(String userId) throws InterruptedException {

        if(Math.random() > 0.5) {
            Thread.sleep(3000);
            throw new RuntimeException("Failed!");
        } else {
            return userRepo.findById(userId);
        }

    }

    public void setUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> fallback(String userId) throws InterruptedException {
        User user = new User();
        user.setUserEmail("x@y.com");
        user.setUserId("1111");
        user.setUserName("aaaa");
        return Optional.ofNullable(null);
    }
}
