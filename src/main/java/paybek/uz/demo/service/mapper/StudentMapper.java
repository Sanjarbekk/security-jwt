package paybek.uz.demo.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.service.dto.StudentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    @Mapping(source = "province.id", target = "provinceId")
    StudentDTO toDto(Student student);

    @Mapping(source = "provinceId", target = "province.id")
    Student toEntity(StudentDTO studentDTO);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
