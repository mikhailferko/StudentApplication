package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.repository.StudentRepository;
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
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository repository;


    private StudentEntity createTestStudent(String surname) {
        StudentEntity emp = new StudentEntity(null, surname);
        return repository.saveAndFlush(emp);
    }

    @Test
    public void saveStudentTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO(null, "QWERT");
        ResponseEntity<String> response = restTemplate.postForEntity("/student", studentDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
        repository.deleteAll(repository.findAllBySurname("QWERT"));
    }

    @Test
    public void getStudentTest() throws Exception {
        long id = createTestStudent("Петросян").getId();
        ResponseEntity<StudentDTO> response = restTemplate.exchange("/student/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<StudentDTO>() {
                }, id);
        StudentDTO dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.getSurname(), is("Петросян"));
        repository.delete(repository.findById(id).get());
    }

    @Test
    public void deleteStudentTest() throws Exception {
        long id = createTestStudent("Пушкин").getId();
        ResponseEntity<String> response = restTemplate.exchange("/student/{id}", HttpMethod.DELETE,null, String.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getAllStudentTest() throws Exception {
        long id = createTestStudent("Толстой").getId();
        long id1 = createTestStudent("Тургенев").getId();
        ResponseEntity<List<StudentDTO>> response = restTemplate.exchange("/student",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StudentDTO>>() {
                });
        List<StudentDTO> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.isEmpty(), is(false));
        repository.delete(repository.findById(id).get());
        repository.delete(repository.findById(id1).get());
    }
}
