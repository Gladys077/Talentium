package cohorte16.homeservice.dtos;

import java.math.BigDecimal;

public record OrderRatingDTO(
        Long id,
        Integer ratingClient,
        Integer ratingProfessional,
        BigDecimal wallet

) {
}
