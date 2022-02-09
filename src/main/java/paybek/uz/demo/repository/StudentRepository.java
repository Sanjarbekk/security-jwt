package paybek.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paybek.uz.demo.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}