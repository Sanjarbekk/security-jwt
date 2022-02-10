package paybek.uz.demo.controller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paybek.uz.demo.domain.Province;
import paybek.uz.demo.service.ProvinceService;
import paybek.uz.demo.service.dto.ProvinceDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    private final static Logger log = LoggerFactory.getLogger(ProvinceController.class);

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProvinceDTO> createProvince(@Valid @RequestBody ProvinceDTO provinceDTO) {
        log.debug("Rest request to save province ; {}", provinceDTO);
        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProvinceDTO>> getAll() {
        log.debug("Get All Province ");
        return ResponseEntity.ok()
                .body(provinceService.getAllProvince());
    }

    @PutMapping("/update")
    public ResponseEntity<ProvinceDTO> update(@Valid @RequestBody ProvinceDTO provinceDTO) {
        log.debug("Update this Province : {}", provinceDTO);
        ProvinceDTO result = provinceService.update(provinceDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String result = provinceService.delete(id);
        return ResponseEntity.ok()
                .body(result);
    }
}