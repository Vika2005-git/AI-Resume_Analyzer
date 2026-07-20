package com.vika.airesumeanalyzer.controller;

import com.vika.airesumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vika.airesumeanalyzer.model.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vika.airesumeanalyzer.model.Resume;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api")
public class HelloController {
     
	private final ResumeService resumeService;
	public HelloController(ResumeService resumeService) {
	    this.resumeService = resumeService;
	}
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
    @GetMapping("/studentinfo") //request parameter is used
    public ApiResponse studentInfo(
            @RequestParam String name,
            @RequestParam int age) {

        return new ApiResponse(
                "Student " + name + " is " + age + " years old.",
                "Success",
                "1.0"
        );
    }
    @PostMapping("/resume")
    public ResponseEntity<ApiResponse> createResume(@RequestBody Resume resume) {

    	String message=resumeService.processResume(); 
    	
    	return ResponseEntity.status(201).body(
    	        new ApiResponse(
    	                message,
    	                "Success",
    	                "1.0"
    	        )
    	);
    }
}