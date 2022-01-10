package moySklad.studentApp.StudentApplication.mapper;

import moySklad.studentApp.StudentApplication.dto.MarkDTO;
import moySklad.studentApp.StudentApplication.entity.MarkEntity;
import moySklad.studentApp.StudentApplication.entity.StudentEntity;
import moySklad.studentApp.StudentApplication.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface MarkMapper {

    @Mapping(target = "student", source = "dto", qualifiedByName = "student")
    @Mapping(target = "subject", source = "dto", qualifiedByName = "subject")
    MarkEntity markDTOToMarkEntity(MarkDTO dto);

    @Named("student")
    @Mapping(target = "id", source = "student")
    StudentEntity toEntityStudent(MarkDTO dto);

    @Named("subject")
    @Mapping(target = "id", source = "subject")
    SubjectEntity toEntitySubject(MarkDTO dto);

//    @Mapping(target = "student", expression = "java(String.valueOf(mark.getStudent().getSurname()))")
//    @Mapping(target = "subject", expression = "java(String.valueOf(mark.getSubject().getName()))")
//    MarkDTO markEntityToMarkDTO(MarkEntity mark);
}
