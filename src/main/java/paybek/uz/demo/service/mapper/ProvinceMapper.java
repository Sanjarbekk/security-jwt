package paybek.uz.demo.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import paybek.uz.demo.domain.Province;
import paybek.uz.demo.domain.Student;
import paybek.uz.demo.service.dto.ProvinceDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {
    default Province fromId(Long id) {
        if(id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
        return province;
    }

    @Mapping(target = "name", source = "name")
    Province toEntity(ProvinceDTO provinceDTO);

}