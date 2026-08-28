package com.vika.airesumeanalyzer.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service; 
import com.vika.airesumeanalyzer.repository.ResumeRepository;
import com.vika.airesumeanalyzer.exception.ResumeNotFoundException;
import com.vika.airesumeanalyzer.model.Resume;
import com.vika.airesumeanalyzer.dto.ResumeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ResumeService {
	private final ResumeRepository resumeRepository;

	public ResumeService(ResumeRepository resumeRepository) {
	    this.resumeRepository = resumeRepository;
	}

	public String processResume(ResumeDTO resumeDTO) {

	    Resume resume = convertToEntity(resumeDTO);

	    resumeRepository.save(resume);

	    return "Resume received successfully!";
	}

public List<ResumeDTO> getAllResumes(){
	List<Resume> resumes=resumeRepository.findAll();
	return resumes.stream()
			.map(this::convertToDTO)
			.toList();
}
public List<ResumeDTO> searchBySkill(String skill) {

    List<Resume> resumes =
            resumeRepository.findBySkillsContainingIgnoreCase(skill);

    return resumes.stream()
            .map(this::convertToDTO)
            .toList();
}

public ResumeDTO getResumeById(Integer id) {

    Optional<Resume> existingResume = resumeRepository.findById(id);

    if (existingResume.isPresent()) {
        Resume resume = existingResume.get();
        return convertToDTO(resume);
    }

    throw new ResumeNotFoundException(
            "Resume not found with id " + id 
    );
}

public ResumeDTO updateResume(Integer id, ResumeDTO updatedDTO) {

    Optional<Resume> existingResume = resumeRepository.findById(id);

    if (existingResume.isPresent()) {

        Resume resume = existingResume.get();

        resume.setName(updatedDTO.getName());
        resume.setEmail(updatedDTO.getEmail());
        resume.setSkills(updatedDTO.getSkills());

        Resume updatedResume = resumeRepository.save(resume);

        return convertToDTO(updatedResume);
    }

    throw new ResumeNotFoundException(
            "Resume not found with id: " + id
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
private Resume convertToEntity(ResumeDTO dto) {

    Resume resume = new Resume();

    resume.setName(dto.getName());
    resume.setEmail(dto.getEmail());
    resume.setSkills(dto.getSkills());

    return resume;
}
private ResumeDTO convertToDTO(Resume resume) {

    return new ResumeDTO(
            resume.getName(),
            resume.getEmail(),
            resume.getSkills()
    );
}
public Page<ResumeDTO> getResumesWithPagination(Pageable pageable) {
    Page<Resume> resumes = resumeRepository.findAll(pageable);
    return resumes.map(this::convertToDTO);
}

}