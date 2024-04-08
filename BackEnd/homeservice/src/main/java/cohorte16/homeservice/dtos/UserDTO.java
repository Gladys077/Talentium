package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.User;

public record UserDTO (Long id,
                       String email,
                       String avatar){
}
