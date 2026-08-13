package com.vika.airesumeanalyzer.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service; 
import com.vika.airesumeanalyzer.repository.ResumeRepository;
import com.vika.airesumeanalyzer.exception.ResumeNotFoundException;
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

public Resume getResumeById(Integer id) {

    Optional<Resume> resume = resumeRepository.findById(id);

    if (resume.isPresent()) {
        return resume.get();
    }

    throw new ResumeNotFoundException(
            "Resume with ID " + id + " not found"
    );
}

public Resume updateResume(Integer id,Resume updatedResume) {
	Optional<Resume> existingResume=resumeRepository.findById(id);
	if(existingResume.isPresent()) {
		Resume resume=existingResume.get();
		
		resume.setName(updatedResume.getName());
		resume.setEmail(updatedResume.getEmail());
		resume.setSkills(updatedResume.getSkills());

		return resumeRepository.save(resume);
	}
	throw new ResumeNotFoundException(
			"Resume with ID "+ id + "not found" 
			);
	
}
public boolean deleteResume(Integer id) {
	if(resumeRepository.existsById(id)) {
		resumeRepository.deleteById(id);
		return true;
	}
	 throw new ResumeNotFoundException(
	            "Resume with ID " + id + " not found"
	    );
	
}

}