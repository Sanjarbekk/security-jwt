package paybek.uz.demo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "age")
    @Min(10)
    @Max(120)
    private int age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}