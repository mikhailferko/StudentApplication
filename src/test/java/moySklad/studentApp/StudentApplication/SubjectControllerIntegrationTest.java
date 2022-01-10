package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.dto.SubjectDTO;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
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
public class SubjectControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SubjectRepository repository;

    private SubjectEntity createTestSubject(String name) {
        SubjectEntity emp = new SubjectEntity(null, name);
        return repository.saveAndFlush(emp);
    }

    @Test
    public void saveSubjectTest() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO(null, "QWERTY");
        SubjectDTO subjectDTO1 = new SubjectDTO(null, "QWERTY");
        ResponseEntity<String> response = restTemplate.postForEntity("/subject", subjectDTO, String.class);
        ResponseEntity<String> response1 = restTemplate.postForEntity("/subject", subjectDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response1.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().toString(), is("OK"));
        repository.deleteAll(repository.findByName("QWERTY"));
    }

    @Test
    public void getSubjectTest() throws Exception {
        long id = createTestSubject("TERVER").getId();
        ResponseEntity<SubjectDTO> response = restTemplate.exchange("/subject/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<SubjectDTO>() {
                }, id);
        SubjectDTO dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.getName(), is("TERVER"));
        repository.delete(repository.findById(id).get());
    }

    @Test
    public void deleteSubjectTest() throws Exception {
        long id = createTestSubject("SOPROMAT").getId();
        ResponseEntity<String> response = restTemplate.exchange("/subject/{id}", HttpMethod.DELETE,null, String.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getAllSubjectTest() throws Exception {
        long id = createTestSubject("LINAL").getId();
        long id1 = createTestSubject("VISHMAT").getId();
        ResponseEntity<List<SubjectDTO>> response = restTemplate.exchange("/subject",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<SubjectDTO>>() {
                });
        List<SubjectDTO> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.isEmpty(), is(false));
        repository.delete(repository.findById(id).get());
        repository.delete(repository.findById(id1).get());
    }
}
