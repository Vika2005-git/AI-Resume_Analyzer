package com.vika.airesumeanalyzer.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vika.airesumeanalyzer.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    List<Resume> findBySkillsContainingIgnoreCase(String skill);
    Page<Resume> findAll(Pageable pageable);

}