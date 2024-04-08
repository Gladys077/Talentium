package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;

public record ProfessionalPutDTO(
   
        String phone,
        String cuit,
        String cbu,

        Profession profession,
        DirectionDTO direction

) {
}
