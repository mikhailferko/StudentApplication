package moySklad.studentApp.StudentApplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentDTO {


    private Long id;

    @NotBlank
    private String surname;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String surname) {
        this.id = id;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
