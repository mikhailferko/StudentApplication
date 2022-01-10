package moySklad.studentApp.StudentApplication.mapper;

import moySklad.studentApp.StudentApplication.dto.StudentDTO;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity studentDTOToStudentEntity(StudentDTO studentDTO);

    StudentDTO studentEntityToStudentDTO(StudentEntity student);
}
