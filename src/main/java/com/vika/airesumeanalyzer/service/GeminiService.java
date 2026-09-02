package com.vika.airesumeanalyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vika.airesumeanalyzer.dto.AiAnalysisDTO;

/*@Service
public class GeminiService {
	private final ObjectMapper objectMapper = new ObjectMapper();	

	private final Client client;

	public GeminiService(@Value("${gemini.api.key}") String apiKey) {
		this.client = Client.builder().apiKey(apiKey).build();
	}

	public AiAnalysisDTO analyzeResume(String resumeText) {

		String prompt = """
				Analyze the following resume for a software developer role.

				Return ONLY valid JSON.
				Do not use markdown.
				Do not add ```json.
				Do not add any explanation outside the JSON.

				Use exactly these fields:

				{
				  "keySkills": "...",
				  "strengths": "...",
				  "weaknesses": "...",
				  "recommendedSkills": "...",
				  "improvementSuggestions": "...",
				  "overallAssessment": "...",
				  "ATSScore": "..."

				}

				Keep the analysis concise and practical for a fresher.

				Resume:
				""" + resumeText;

		GenerateContentResponse response = client.models.generateContent(
				                        "gemini-3.6-flash", 
				                        prompt, 
				                        null
				                        );

		try {
		    return objectMapper.readValue(response.text(), AiAnalysisDTO.class);
		} catch (Exception e) {
		    throw new RuntimeException("Failed to parse AI response", e);
		}
	}
	}*/


@Service
public class GeminiService {

    public AiAnalysisDTO analyzeResume(String resumeText) {

        return new AiAnalysisDTO(
                "Java, Spring Boot, MySQL, REST API, Git",
                "Good backend fundamentals and hands-on Spring Boot project experience",
                "Limited professional experience and advanced frontend skills",
                "React, AWS, Docker, Microservices",
                "Improve DSA, build more projects, and strengthen React and cloud skills",
                "Good starting profile for a software developer fresher",
                "78"
        );
    }
}