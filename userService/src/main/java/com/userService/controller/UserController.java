package com.userService.controller;

import com.userService.entities.User;
import com.userService.service.ServiceLayer;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ServiceLayer serviceLayer;

    public UserController(ServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return serviceLayer.getUser(userId);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<User> users = serviceLayer.getAllUsers(page, size);
        return ResponseEntity.ok(users); // Return ResponseEntity for better HTTP response control
    }



    @PostMapping()
    public User createNewUser(@RequestBody User user){
        return serviceLayer.createNewUser(user);
    }

    @PostMapping("/bulk")
    public List<User> createNewUsers(@RequestBody List<User> users) {
        return serviceLayer.createNewUsers(users);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        serviceLayer.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@RequestBody Map<String, Object> update , String userId){
        return serviceLayer.updateUser(update, userId);
    }







}
