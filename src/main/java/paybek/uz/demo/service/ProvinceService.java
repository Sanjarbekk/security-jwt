package paybek.uz.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import paybek.uz.demo.domain.Province;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.repository.ProvinceRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProvinceService {

    private final Logger log = LoggerFactory.getLogger(ProvinceService.class);
    private final ProvinceRepository provinceRepository;
    private final StudentService studentService;

    public ProvinceService(ProvinceRepository provinceRepository, StudentService studentService) {
        this.provinceRepository = provinceRepository;
        this.studentService = studentService;
    }

    public Province save(Province province) {
        log.debug("Request to save province : {}", province);
        return provinceRepository.save(province);
    }

    public List<Province> getAllProvince() {
        log.debug("Get All province");
        return provinceRepository.findAll();
    }

    public Province update(Province province) {
        log.debug("Update this province id : {}, name : {}", province.getId(), province.getName());
        return provinceRepository.save(province);
    }

    public String delete(long id) {
        log.debug("Delete This province : {}", id);
        String result = "Complete Succesfuly";
        if(!checkProvince(id)){
            provinceRepository.deleteById(id);
        } else {
            result = " Deleted Failed";
        }
        return result;
    }

    private boolean checkProvince(long id) {
        List<Student> getAllStudent = studentService.getAllStudent();
        for(Student student: getAllStudent) {
            if(student.getProvince().getId() == id)
                return true;
        }
        return false;
    }
}