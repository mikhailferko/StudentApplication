package moySklad.studentApp.StudentApplication.service.student;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    public StudentDTO getById(Long id);

    public List<StudentDTO> getAll();

    public void addStudent(StudentDTO studentDTO);

    public void updateStudent(StudentDTO studentDTO);

    public void deleteStudent(Long id);
}
