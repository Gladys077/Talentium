package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Direction;

public record ClientUserDTO(Long id, String name, String lastname, String phone,
                            String dni,

                            LoginDTO user, Direction direction, Integer rating,

                            Boolean active) {
    public ClientUserDTO(Client client) {
        this(client.getId(),client.getName(),client.getLastname(), client.getPhone(),client.getDni() ,new LoginDTO(client.getUser()),client.getDirection(),client.getRating(),client.getActive());
    }


}
