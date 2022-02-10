package paybek.uz.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.repository.StudentRepository;
import paybek.uz.demo.service.dto.StudentDTO;
import paybek.uz.demo.service.mapper.StudentMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public List<StudentDTO> getAllStudent() {
        log.debug("Get All student");
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO update(StudentDTO studentDTO) {
        return save(studentDTO);
    }

    public void delete(long id) {
        log.debug("Delete This student : {}", id);
        studentRepository.deleteById(id);
    }
}