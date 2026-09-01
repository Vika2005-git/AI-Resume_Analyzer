package com.vika.airesumeanalyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

	private final Client client;

	public GeminiService(@Value("${gemini.api.key}") String apiKey) {
		this.client = Client.builder().apiKey(apiKey).build();
	}

	public String analyzeResume(String resumeText) {

		String prompt = """
				Analyze the following resume.

				Give:
				1. Key skills
				2. Strengths
				3. Missing or recommended skills/Keywords
				4. Overall improvement suggestions
				5. ATS Score 

				Resume:
				""" + resumeText;

		GenerateContentResponse response = client.models.generateContent("gemini-3.6-flash", prompt, null);

		return response.text();
	}
}