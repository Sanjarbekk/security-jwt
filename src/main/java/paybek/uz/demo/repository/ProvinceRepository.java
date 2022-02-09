package paybek.uz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paybek.uz.demo.domain.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}