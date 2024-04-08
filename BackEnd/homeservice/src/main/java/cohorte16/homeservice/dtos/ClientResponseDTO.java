package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Direction;
import cohorte16.homeservice.models.User;

public record ClientResponseDTO(

        Long clientId,
        String name,
        String lastname,
        String phone,
        String dni,
        Integer rating,
        UserDTO user,
        DirectionDTO direction
) {

}
