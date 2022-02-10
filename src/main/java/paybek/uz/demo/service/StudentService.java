package paybek.uz.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        log.debug("Request to save Student : {}", student);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        log.debug("Get All student");
        return studentRepository.findAll();
    }

    public Student update(Student student) {
        log.debug("Update this student id : {}, name : {}", student.getId(), student.getFirstName());
        return studentRepository.save(student);
    }

    public void delete(long id) {
        log.debug("Delete This student : {}", id);
        studentRepository.deleteById(id);
    }
}