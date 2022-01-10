package moySklad.studentApp.StudentApplication.service.subject;

import moySklad.studentApp.StudentApplication.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    public SubjectDTO getById(Long id);

    public List<SubjectDTO> getAll();

    public void addSubject(SubjectDTO subjectDTO);

    public void updateSubject(SubjectDTO subjectDTO);

    public void deleteSubject(Long id);
}
