package cohorte16.homeservice.dtos;

import java.math.BigDecimal;

public record OrderAceptedDTO(
        Long id,
        String description,
        String descriptionProfessional,
        BigDecimal price,
        Long idClient,
        ProfessionalOrderDTO professional
) {
}
