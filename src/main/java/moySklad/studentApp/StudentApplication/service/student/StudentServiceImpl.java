package moySklad.studentApp.StudentApplication.service.student;

import moySklad.studentApp.StudentApplication.exception.NotFoundException;
import moySklad.studentApp.StudentApplication.mapper.StudentMapper;
import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentDTO getById(Long id) {
        StudentEntity student = repository.findById(id).orElseThrow(() -> new NotFoundException("Такого студента не существует"));
        return mapper.studentEntityToStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = repository.findAll();
        List<StudentDTO> students = new ArrayList<>();
        for (StudentEntity student : iterable) {
            students.add(mapper.studentEntityToStudentDTO(student));
        }
        return students;
    }

    @Override
    public void addStudent(StudentDTO studentDTO) {
        StudentEntity student = mapper.studentDTOToStudentEntity(studentDTO);
        repository.save(student);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        StudentEntity student = repository.findById(studentDTO.getId()).orElseThrow(() -> new NotFoundException("Такого студента не существует"));
        student = mapper.studentDTOToStudentEntity(studentDTO);
        repository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        StudentEntity student = repository.findById(id).orElseThrow(() -> new NotFoundException("Такого студента не существует"));
        repository.delete(student);
    }
}
