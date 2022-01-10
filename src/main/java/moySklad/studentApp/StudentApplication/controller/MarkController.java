package moySklad.studentApp.StudentApplication.controller;

import moySklad.studentApp.StudentApplication.dto.MarkDTO;
import moySklad.studentApp.StudentApplication.dto.MarkDTOOut;
import moySklad.studentApp.StudentApplication.service.mark.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mark")
public class MarkController {

    private final MarkService service;

    @Autowired
    public MarkController(MarkService service) {
        this.service = service;
    }

    @GetMapping
    public List<MarkDTOOut> getAllMarks() {
        return service.getAll();
    }

    @GetMapping("{student}")
    public List<MarkDTOOut> getAllMarksByStudent(@PathVariable String student) {
        return service.getByStudent(student);
    }

    @PostMapping
    public String addMark(@Validated @RequestBody MarkDTO dto) {
        service.addMark(dto);
        return "OK";
    }
}
