package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Orderstatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderProfessionalDTO(
        @NotNull
        Long idProfessional, //Id del professional
        String descriptionProfessional,
        BigDecimal price
) {
}
