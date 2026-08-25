package com.vika.airesumeanalyzer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResumeDTO {
	 @NotBlank(message = "Name is required")
    private String name;
	 @NotBlank(message = "Email is required")
	 @Email(message = "Invalid email format")
    private String email;
	 @NotBlank(message = "Skills is required")
    private String skills;

    public ResumeDTO() {
    }

    public ResumeDTO(String name, String email, String skills) {
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}