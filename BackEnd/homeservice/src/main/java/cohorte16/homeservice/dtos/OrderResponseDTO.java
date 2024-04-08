package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Orderstatus;

import java.math.BigDecimal;
import java.util.Date;

public record OrderResponseDTO(
        Long id,
        String description,
        String professionalDescription,
        BigDecimal price,
        Date date,
        Orderstatus orderstatus,
        ClientResponseDTO client,
        ProfessionalResponseDTO professional

) {
}
