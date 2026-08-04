package com.vika.airesumeanalyzer.service;
import java.util.List;
import org.springframework.stereotype.Service; 
import com.vika.airesumeanalyzer.repository.ResumeRepository;
import com.vika.airesumeanalyzer.model.Resume;
@Service
public class ResumeService {
	private final ResumeRepository resumeRepository;

	public ResumeService(ResumeRepository resumeRepository) {
	    this.resumeRepository = resumeRepository;
	}

public String processResume(Resume resume) {
	resumeRepository.save(resume);
    return "Resume received successfully!";
}
public List<Resume> getAllResumes(){
	return resumeRepository.findAll();
}
}