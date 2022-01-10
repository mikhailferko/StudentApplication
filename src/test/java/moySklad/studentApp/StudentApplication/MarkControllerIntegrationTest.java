package moySklad.studentApp.StudentApplication;

import moySklad.studentApp.StudentApplication.dto.MarkDTO;
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
public class MarkControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void saveMarkTest() throws Exception {
        MarkDTO markDTO = new MarkDTO(1L, 1L, 2);
        ResponseEntity<String> response = restTemplate.postForEntity("/mark", markDTO, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getMarkTest() throws Exception {
        ResponseEntity<MarkDTO> response = restTemplate.exchange("/mark/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<MarkDTO>() {
                });
        MarkDTO dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.getMark(), notNullValue());
        assertThat(dto.getStudent(), notNullValue());
        assertThat(dto.getSubject(), notNullValue());
    }

    @Test
    public void deleteMarkTest() throws Exception {
        Long id = 1L;
        ResponseEntity<String> response = restTemplate.exchange("/mark/{id}", HttpMethod.DELETE,null, String.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is("OK"));
    }

    @Test
    public void getAllMarkTest() throws Exception {
        ResponseEntity<List<MarkDTO>> response = restTemplate.exchange("/mark",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<MarkDTO>>() {
                });
        List<MarkDTO> dto = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(dto.get(0).getSubject(), notNullValue());
        assertThat(dto.get(0).getStudent(), notNullValue());
        assertThat(dto.get(0).getMark(), notNullValue());
    }
}
