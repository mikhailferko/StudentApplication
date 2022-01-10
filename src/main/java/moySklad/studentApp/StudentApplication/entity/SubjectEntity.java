package moySklad.studentApp.StudentApplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class SubjectEntity implements Comparable<SubjectEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public SubjectEntity() {
    }

    public SubjectEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(SubjectEntity o) {
        return this.name.compareTo(o.getName());
    }
}

