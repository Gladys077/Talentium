package cohorte16.homeservice.mappers;

import cohorte16.homeservice.dtos.*;
import cohorte16.homeservice.models.Professional;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProfessionalMapper {

    public ProfessionalMiniDTO professionalToProfessionalMiniDTO(Professional professional){
        return new ProfessionalMiniDTO(
                professional.getId(),
                professional.getName(),
                professional.getLastname(),
                professional.getPhone(),
                professional.getProfession(),
                professional.getRating()
        );
    }

    public ProfessionalDTO professionalToProfessionalDTO(Professional professional){
        return new ProfessionalDTO(
                professional.getId(),
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

    public Professional professionalDTOToProfessional(ProfessionalDTO professionalDTO){
        return new Professional(
                null,
                professionalDTO.name(),
                professionalDTO.lastname(),
                professionalDTO.phone(),
                professionalDTO.cuit(),
                professionalDTO.cbu(),
                professionalDTO.rating(),
                professionalDTO.profession(),
                professionalDTO.direction(),
                professionalDTO.user(),
                Collections.emptyList(),
                Boolean.TRUE,
                professionalDTO.wallet(),
                null
        );
    }

    public ProfessionalResponseDTO professionalToProfessionalResponseDTO(Professional professional){
        return new ProfessionalResponseDTO(
                professional.getId(),
                professional.getName(),
                professional.getLastname(),
                professional.getPhone(),
                professional.getCbu(),
                professional.getCuit(),
                professional.getRating(),
                professional.getProfession(),
                new DirectionDTO(professional.getDirection().getStreet(),
                        professional.getDirection().getNumber(),
                        professional.getDirection().getProvince(),
                        professional.getDirection().getLocation()),
                new UserDTO(
                        professional.getUser().getId(),
                        professional.getUser().getEmail(),
                        professional.getUser().getAvatar())
        );
    }
}
