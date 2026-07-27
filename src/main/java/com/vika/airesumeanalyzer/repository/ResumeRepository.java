package com.vika.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vika.airesumeanalyzer.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}