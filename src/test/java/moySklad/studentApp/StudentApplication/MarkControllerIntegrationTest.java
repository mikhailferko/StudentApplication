package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.MarkDTO;
import moySklad.studentApp.StudentApplication.dto.MarkDTOOut;
import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.entity.MarkEntity;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import moySklad.studentApp.StudentApplication.repository.MarkRepository;
import moySklad.studentApp.StudentApplication.repository.StudentRepository;
import moySklad.studentApp.StudentApplication.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarkControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MarkRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private MarkEntity createTestMark(StudentEntity student, SubjectEntity subject, int mark) {
        MarkEntity emp = new MarkEntity(null, student, subject, mark);
        return repository.saveAndFlush(emp);
    }

    private StudentEntity createTestStudent(String surname) {
        StudentEntity emp = new StudentEntity(null, surname);
        return studentRepository.saveAndFlush(emp);
    }

    private SubjectEntity createTestSubject(String name) {
        SubjectEntity emp = new SubjectEntity(null, name);
        return subjectRepository.saveAndFlush(emp);
    }

    @Test
    public void saveMarkTest() throws Exception {
        long id = createTestStudent("zxcv").getId();
        long id1 = createTestSubject("zxcv").getId();
        MarkDTO markDTO = new MarkDTO(id, id1, 2);
        ResponseEntity<String> response = restTemplate.postForEntity("/mark", markDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
        repository.deleteAll(repository.findAllByStudent(studentRepository.findById(id).get()));
        studentRepository.delete(studentRepository.findById(id).get());
        subjectRepository.delete(subjectRepository.findById(id1).get());
    }

    @Test
    public void getMarkByStudentTest() throws Exception {
        long id = createTestStudent("zxcv").getId();
        long id1 = createTestSubject("zxcv").getId();
        long markId = createTestMark(studentRepository.findById(id).get(), subjectRepository.findById(id1).get(), 3).getId();
        String studentSurname = studentRepository.findById(id).get().getSurname();
        ResponseEntity<List<MarkDTOOut>> response = restTemplate.exchange("/mark/{studentSurname}",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<MarkDTOOut>>() {
                }, studentSurname);
        List<MarkDTOOut> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.get(0).getMarks(), notNullValue());
        assertThat(dto.get(0).getStudent(), is("zxcv"));
        repository.delete(repository.findById(markId).get());
        studentRepository.delete(studentRepository.findById(id).get());
        subjectRepository.delete(subjectRepository.findById(id1).get());
    }

    @Test
    public void getAllMarkTest() throws Exception {
        long id = createTestStudent("cvbn").getId();
        long id1 = createTestSubject("cvbn").getId();
        long markId = createTestMark(studentRepository.findById(id).get(), subjectRepository.findById(id1).get(), 3).getId();
        ResponseEntity<List<MarkDTOOut>> response = restTemplate.exchange("/mark",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<MarkDTOOut>>() {
                });
        List<MarkDTOOut> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.isEmpty(), is(false));
        repository.delete(repository.findById(markId).get());
        studentRepository.delete(studentRepository.findById(id).get());
        subjectRepository.delete(subjectRepository.findById(id1).get());
    }
}
