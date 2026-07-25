package com.vika.airesumeanalyzer.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name= "resume")
public class Resume {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

    private String name;
    private String email;
    private String skills;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Resume() {
    }

    public Resume(String name, String email, String skills) {
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