package moySklad.studentApp.StudentApplication.repository;

import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    Iterable<StudentEntity> findAllBySurname(String surname);

}
