package moySklad.studentApp.StudentApplication.service.subject;

import moySklad.studentApp.StudentApplication.dto.SubjectDTO;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import moySklad.studentApp.StudentApplication.exception.NotFoundException;
import moySklad.studentApp.StudentApplication.exception.SubjectNotUniqueException;
import moySklad.studentApp.StudentApplication.mapper.SubjectMapper;
import moySklad.studentApp.StudentApplication.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;
    private final SubjectMapper mapper;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository, SubjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SubjectDTO getById(Long id) {
        SubjectEntity subject = repository.findById(id).orElseThrow(() -> new NotFoundException("Такого предмета не существует"));
        return mapper.subjectEntityToSubjectDTO(subject);
    }

    @Override
    public List<SubjectDTO> getAll() {
        Iterable<SubjectEntity> iterable = repository.findAll();
        List<SubjectDTO> subjects = new ArrayList<>();
        for (SubjectEntity subject : iterable) {
            subjects.add(mapper.subjectEntityToSubjectDTO(subject));
        }
        return subjects;
    }

    @Override
    public void addSubject(SubjectDTO subjectDTO) {
        Iterable<SubjectEntity> subjects = repository.findByName(subjectDTO.getName());
        if (subjects.iterator().hasNext()) {
            throw new SubjectNotUniqueException("Такой предмет уже существует");
        }
        SubjectEntity subject = mapper.subjectDTOToSubjectEntity(subjectDTO);
        repository.save(subject);
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) {
        SubjectEntity subject = repository.findById(subjectDTO.getId()).orElseThrow(() -> new SubjectNotUniqueException("Такого предмета не существует"));
        subject = mapper.subjectDTOToSubjectEntity(subjectDTO);
        repository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        SubjectEntity subject = repository.findById(id).orElseThrow(() -> new SubjectNotUniqueException("Такого предмета не существует"));
        repository.delete(subject);
    }
}
