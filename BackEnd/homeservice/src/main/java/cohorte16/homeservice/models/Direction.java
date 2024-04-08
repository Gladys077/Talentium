package cohorte16.homeservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name="direcciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="calle")
    @NotBlank
    private String street;

    @NotBlank
    @Column(name="numero")
    private String number;

    @NotBlank
    @Column(name="provincia")
    private String province;

    @NotBlank
    @Column(name="localidad")
    private String location;

}
