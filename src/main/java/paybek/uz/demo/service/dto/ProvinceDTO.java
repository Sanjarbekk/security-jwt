package paybek.uz.demo.service.dto;

import java.util.Objects;

public class ProvinceDTO {
    private Long id;

    private String name;

    public ProvinceDTO() {
    }

    public ProvinceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceDTO that = (ProvinceDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProvinceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}