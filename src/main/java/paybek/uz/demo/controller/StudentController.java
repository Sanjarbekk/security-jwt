package paybek.uz.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.service.StudentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        log.debug("Rest request to save Student : {}", student);
        Student result = studentService.save(student);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        log.debug("Rest request get all Students");
        try {
            List<Student> studentList = new ArrayList<>();
            studentList = studentService.getAllStudent();
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        log.debug("Rest request to update Student : {}", student);
        Student result;
        if (student.getId() != null)
            result = studentService.update(student);
        else
            result = studentService.save(student);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("Rest request to delete Student : {}", id);
        studentService.delete(id);
        return  ResponseEntity.ok().build();
    }
}