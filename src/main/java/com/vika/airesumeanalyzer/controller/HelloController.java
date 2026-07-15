package com.vika.airesumeanalyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vika.airesumeanalyzer.model.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public ApiResponse hello(@PathVariable String name) {

        return new ApiResponse(
                "Hello " + name + " welcome to SpringBoot!",
                "Success",
                "1.0"
        );
    }

    @GetMapping("/student/{name}/{age}")
public ApiResponse student(@PathVariable String name,
                           @PathVariable int age) {

    return new ApiResponse(
            "Student " + name + " is " + age + " years old.",
            "Success",
            "1.0"
    );
}
    @GetMapping("/studentinfo")
    public ApiResponse studentInfo(
            @RequestParam String name,
            @RequestParam int age) {

        return new ApiResponse(
                "Student " + name + " is " + age + " years old.",
                "Success",
                "1.0"
        );
    }
}