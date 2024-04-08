package cohorte16.homeservice.dtos;

import java.math.BigDecimal;

public record OrderGetPendRespDTO(
        Long id,
        String description,
        String descriptionProfessional,
        BigDecimal price,
        Long idClient,
        ProfessionalMiniDTO professional

) {
}
