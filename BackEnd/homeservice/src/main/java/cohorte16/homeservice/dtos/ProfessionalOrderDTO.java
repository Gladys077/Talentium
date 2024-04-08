package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;

public record ProfessionalOrderDTO(
        Long id,
        String name,
        String lastname,
        String phone,
        String email,
        String cuit,
        String cbu,
        Integer rating,
        Profession profession,
        DirectionDTO direction
) {
}
