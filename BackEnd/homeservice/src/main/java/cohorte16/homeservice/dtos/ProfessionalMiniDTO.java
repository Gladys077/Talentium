package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;

public record ProfessionalMiniDTO(
        Long id,
        String name,
        String lastname,
        String phone,
        Profession profession,
        Integer rating
) {
}
