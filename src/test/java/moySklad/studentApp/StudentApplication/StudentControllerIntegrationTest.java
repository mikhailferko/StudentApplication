package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
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
    TestRestTemplate restTemplate;

    @Test
    public void saveStudentTest() throws Exception {
        StudentDTO studentDTO = new StudentDTO(null, "QWERT");
        ResponseEntity<String> response = restTemplate.postForEntity("/student", studentDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getStudentTest() throws Exception {
        ResponseEntity<StudentDTO> response = restTemplate.exchange("/student/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<StudentDTO>() {
                });
        StudentDTO dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.getSurname(), notNullValue());
    }

    @Test
    public void deleteStudentTest() throws Exception {
        Long id = 1L;
        ResponseEntity<String> response = restTemplate.exchange("/student/{id}", HttpMethod.DELETE,null, String.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getAllStudentTest() throws Exception {
        ResponseEntity<List<StudentDTO>> response = restTemplate.exchange("/student",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StudentDTO>>() {
                });
        List<StudentDTO> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.get(0).getSurname(), notNullValue());
    }
}
