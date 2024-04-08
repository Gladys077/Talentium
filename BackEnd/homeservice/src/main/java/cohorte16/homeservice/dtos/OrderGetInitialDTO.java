package cohorte16.homeservice.dtos;

import java.util.Date;

public record OrderGetInitialDTO(
        Long id,
        String description,
        Date date,
        Long clientId
) {
}
