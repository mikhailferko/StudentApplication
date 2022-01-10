package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.dto.SubjectDTO;
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
    TestRestTemplate restTemplate;

    @Test
    public void saveSubjectTest() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO(null, "QWERT");
        ResponseEntity<String> response = restTemplate.postForEntity("/subject", subjectDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getSubjectTest() throws Exception {
        ResponseEntity<SubjectDTO> response = restTemplate.exchange("/subject/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<SubjectDTO>() {
                });
        SubjectDTO dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.getName(), notNullValue());
    }

    @Test
    public void deleteSubjectTest() throws Exception {
        Long id = 1L;
        ResponseEntity<String> response = restTemplate.exchange("/subject/{id}", HttpMethod.DELETE,null, String.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getAllSubjectTest() throws Exception {
        ResponseEntity<List<SubjectDTO>> response = restTemplate.exchange("/subject",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<SubjectDTO>>() {
                });
        List<SubjectDTO> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.get(0).getName(), notNullValue());
    }
}
