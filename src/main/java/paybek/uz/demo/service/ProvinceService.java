package paybek.uz.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import paybek.uz.demo.domain.Province;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.repository.ProvinceRepository;
import paybek.uz.demo.service.dto.ProvinceDTO;
import paybek.uz.demo.service.dto.StudentDTO;
import paybek.uz.demo.service.mapper.ProvinceMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProvinceService {

    private final Logger log = LoggerFactory.getLogger(ProvinceService.class);
    private final ProvinceRepository provinceRepository;
    private final StudentService studentService;
    private final ProvinceMapper provinceMapper;

    public ProvinceService(ProvinceRepository provinceRepository, StudentService studentService, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.studentService = studentService;
        this.provinceMapper = provinceMapper;
    }

    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        log.debug("Request to save province : {}", provinceDTO);
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        return provinceMapper.toDto(province);
    }

    public List<ProvinceDTO> getAllProvince() {
        log.debug("Get All province");
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProvinceDTO update(ProvinceDTO provinceDTO) {
        log.debug("Update this province id : {}, name : {}", provinceDTO.getId(), provinceDTO.getName());
        return save(provinceDTO);
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
        List<StudentDTO> getAllStudent = studentService.getAllStudent();
        for(StudentDTO student: getAllStudent) {
            if(student.getProvinceId() == id)
                return true;
        }
        return false;
    }
}