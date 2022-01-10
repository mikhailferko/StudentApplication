package moySklad.studentApp.StudentApplication.controller;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public String saveStudent(@Validated @RequestBody StudentDTO dto) {
        service.addStudent(dto);
        return "OK";
    }

    @PutMapping
    public String updateStudent(@Valid @RequestBody StudentDTO dto) {
        service.updateStudent(dto);
        return "OK";
    }

    @DeleteMapping("{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "OK";
    }
}
