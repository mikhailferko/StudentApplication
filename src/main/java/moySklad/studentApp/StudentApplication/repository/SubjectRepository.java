package moySklad.studentApp.StudentApplication.repository;

import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {

    Iterable<SubjectEntity> findByName(String name);

    SubjectEntity saveAndFlush(SubjectEntity emp);
}
