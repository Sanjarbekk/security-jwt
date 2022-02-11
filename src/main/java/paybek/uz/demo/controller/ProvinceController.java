package paybek.uz.demo.controller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /** hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'), hasAnyAuthority('permission')
     *
     * Yuqoridagi metodlar ApplicationSecurityConfig dagi quyidagi metodlarga teng kuchli
     *
     *
     *
     *   .antMatchers(HttpMethod.DELETE, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
     *   .antMatchers(HttpMethod.POST, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
     *   .antMatchers(HttpMethod.PUT, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
     *   .antMatchers(HttpMethod.GET, "/api/province/**").hasAnyRole(ADMIN.name(), TRAINER.name())
     *
     *
     *   ApplicationSecurityConfig ga EnableGlobalMethod(prePostEnable = true) ni quyamiz
     * @return
     */

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('province:write')")
    public ResponseEntity<ProvinceDTO> createProvince(@Valid @RequestBody ProvinceDTO provinceDTO) {
        log.debug("Rest request to save province ; {}", provinceDTO);
        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public ResponseEntity<List<ProvinceDTO>> getAll() {
        log.debug("Get All Province ");
        return ResponseEntity.ok()
                .body(provinceService.getAllProvince());
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('province:write')")
    public ResponseEntity<ProvinceDTO> update(@Valid @RequestBody ProvinceDTO provinceDTO) {
        log.debug("Update this Province : {}", provinceDTO);
        ProvinceDTO result = provinceService.update(provinceDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('province:write')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String result = provinceService.delete(id);
        return ResponseEntity.ok()
                .body(result);
    }
}