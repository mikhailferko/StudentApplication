package moySklad.studentApp.StudentApplication.dto;

import java.util.Map;

public class MarkDTOOut {

    private String student;

    private Map<String, Double> marks;

    public MarkDTOOut() {
    }

    public MarkDTOOut(String student, Map<String, Double> marks) {
        this.student = student;
        this.marks = marks;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Map<String, Double> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Double> marks) {
        this.marks = marks;
    }
}
