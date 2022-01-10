package moySklad.studentApp.StudentApplication.controller;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.dto.SubjectDTO;
import moySklad.studentApp.StudentApplication.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {

    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubjectDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public SubjectDTO getSubject(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public String saveSubject(@Validated @RequestBody SubjectDTO dto) {
        service.addSubject(dto);
        return "OK";
    }

    @PutMapping
    public String updateSubject(@Valid @RequestBody SubjectDTO dto) {
        service.updateSubject(dto);
        return "OK";
    }

    @DeleteMapping("{id}")
    public String deleteSubject(@PathVariable Long id) {
        service.deleteSubject(id);
        return "OK";
    }
}
