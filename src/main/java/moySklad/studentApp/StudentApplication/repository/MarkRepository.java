package moySklad.studentApp.StudentApplication.repository;

import moySklad.studentApp.StudentApplication.entity.MarkEntity;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<MarkEntity, Long> {

    Iterable<MarkEntity> findAllByStudent(StudentEntity student);

    MarkEntity saveAndFlush(MarkEntity emp);
}
