package com.vika.airesumeanalyzer.controller;
import java.util.List;
import com.vika.airesumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vika.airesumeanalyzer.model.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;

import com.vika.airesumeanalyzer.model.Resume;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

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
    public ResponseEntity<ApiResponse> createResume(@Valid @RequestBody Resume resume) {

    	String message=resumeService.processResume(resume); 
    	
    	return ResponseEntity.status(201).body(
    	        new ApiResponse(
    	                message,
    	                "Success",
    	                "1.0"
    	        )
    	        
    	);
    }
    
    @PutMapping("/resume/{id}")
    public ResponseEntity<Resume> updateResume(
            @PathVariable Integer id,
            @Valid @RequestBody Resume resume) {

        Resume updatedResume = resumeService.updateResume(id, resume);

        if (updatedResume == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedResume);
    }

@GetMapping("/resumes")
public List<Resume> getAllResumes() {
    return resumeService.getAllResumes();
}

@DeleteMapping("/resume/{id}")
public ResponseEntity<String> deleteResume(@PathVariable Integer id) {

    boolean deleted = resumeService.deleteResume(id);

    if (deleted) {
        return ResponseEntity.ok("Resume deleted successfully!");
    }

    return ResponseEntity.notFound().build();
}
}