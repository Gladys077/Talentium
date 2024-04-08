package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.models.Direction;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.models.User;

import java.math.BigDecimal;

public record ProfessionalDTO(
        Long id,
        String name,
        String lastname,
        String phone,
        String cuit,
        Profession profession,
        Integer rating,
        String cbu,
        User user,
        Direction direction,
        BigDecimal wallet
) {
    public ProfessionalDTO(Professional professional) {
       this(    professional.getId(),
                professional.getName(),
                professional.getLastname(),
                professional.getPhone(),
               professional.getCuit(),
                professional.getProfession(),
                professional.getRating(),
               professional.getCbu(),
               professional.getUser(),
               professional.getDirection(),
               professional.getWallet()
       );
    }

}
