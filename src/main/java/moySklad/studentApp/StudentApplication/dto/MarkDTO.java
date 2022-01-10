package moySklad.studentApp.StudentApplication.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MarkDTO {

    @NotNull
    private Long student;

    @NotNull
    private Long subject;

    @NotNull
    @Min(value = 2, message = "Оценка должна быть в диапазоне от 2 до 5")
    @Max(value = 5, message = "Оценка должна быть в диапазоне от 2 до 5")
    private int mark;

    public MarkDTO() {
    }

    public MarkDTO(Long student, Long subject, int mark) {

        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }



    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
