package moySklad.studentApp.StudentApplication.mapper;

import moySklad.studentApp.StudentApplication.dto.SubjectDTO;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectEntity subjectDTOToSubjectEntity(SubjectDTO subjectDTO);

    SubjectDTO subjectEntityToSubjectDTO(SubjectEntity subject);
}
