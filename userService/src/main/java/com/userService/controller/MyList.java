package com.userService.controller;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/name")
public class MyList {

    @GetMapping("/list")
    public List<String> list(){
        return new ArrayList<>(Arrays.asList("Krushna", "Patil", "Soniya"));
    }
}
