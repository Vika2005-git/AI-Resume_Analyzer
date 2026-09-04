package com.vika.airesumeanalyzer.controller;
import java.util.List;
import com.vika.airesumeanalyzer.service.ResumeService;
import com.vika.airesumeanalyzer.service.ResumeFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vika.airesumeanalyzer.model.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import com.vika.airesumeanalyzer.dto.ResumeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.vika.airesumeanalyzer.service.GeminiService;
import org.apache.tika.Tika;
import com.vika.airesumeanalyzer.dto.AiAnalysisDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class HelloController {
	private final Tika tika = new Tika();
	private final ResumeService resumeService;
    private final GeminiService geminiService;

	public HelloController(ResumeService resumeService,
			                ResumeFileService resumeFileService,
			                GeminiService geminiService) {
	    this.resumeService = resumeService;
	   
	    this.geminiService = geminiService;
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
    public ResponseEntity<ApiResponse> createResume(@Valid @RequestBody ResumeDTO resumeDTO) {

    	String message=resumeService.processResume(resumeDTO); 
    	
    	return ResponseEntity.status(201).body(
    	        new ApiResponse(
    	                message,
    	                "Success",
    	                "1.0"
    	        )
    	        
    	);
    }
    
    @PutMapping("/resume/{id}")
    public ResponseEntity<ResumeDTO> updateResume(
            @PathVariable Integer id,
            @Valid @RequestBody ResumeDTO resumeDTO) {

        ResumeDTO updatedResume = resumeService.updateResume(id, resumeDTO);

        return ResponseEntity.ok(updatedResume);
    }

@GetMapping("/resumes")
public List<ResumeDTO> getAllResumes() {
    return resumeService.getAllResumes();
}

@GetMapping("/resume/{id}")
public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Integer id) {

    ResumeDTO resume = resumeService.getResumeById(id);

    return ResponseEntity.ok(resume);
}

@DeleteMapping("/resume/{id}")
public ResponseEntity<String> deleteResume(@PathVariable Integer id) {

    boolean deleted = resumeService.deleteResume(id);

    if (deleted) {
        return ResponseEntity.ok("Resume deleted successfully!");
    }

    return ResponseEntity.notFound().build();
}
@GetMapping("/resumes/search")
public ResponseEntity<List<ResumeDTO>> searchBySkill(
        @RequestParam String skill) {

    return ResponseEntity.ok(
            resumeService.searchBySkill(skill)
    );
}

@GetMapping("/resumes/page")
public ResponseEntity<Page<ResumeDTO>> getResumesWithPagination(
        Pageable pageable) {

    return ResponseEntity.ok(
            resumeService.getResumesWithPagination(pageable)
    );
}

@PostMapping("/resume/upload")
public ResponseEntity<AiAnalysisDTO> uploadResume(
        @RequestParam("file") MultipartFile file) {

    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body(null);
    }

    String fileName = file.getOriginalFilename();

    if (fileName == null ||
            (!fileName.toLowerCase().endsWith(".pdf")
            && !fileName.toLowerCase().endsWith(".docx"))) {

        return ResponseEntity.badRequest().body(null);
    }

    try {

        // Step 1: Extract text from resume
        String resumeText = tika.parseToString(file.getInputStream());

        // Step 2: Send extracted text to Gemini
        AiAnalysisDTO analysis = geminiService.analyzeResume(resumeText);

        // Step 3: Return AI analysis
        return ResponseEntity.ok(analysis);

    } catch (Exception e) {
        e.printStackTrace();

        return ResponseEntity.internalServerError()
                .body(null);
    }
}

}