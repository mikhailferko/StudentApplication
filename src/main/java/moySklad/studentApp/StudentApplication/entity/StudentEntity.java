package moySklad.studentApp.StudentApplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity implements Comparable<StudentEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String surname) {
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

    @Override
    public int compareTo(StudentEntity o) {
        return this.surname.compareTo(o.getSurname());
    }
}
