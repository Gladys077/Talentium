package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Direction;
import cohorte16.homeservice.models.User;

public record ClientDTO(
        String name,
        String lastname,
        String phone,
        String dni,
        Integer rating,
        User user,
        Direction direction
) {

    public ClientDTO(Client client) {
        this(client.getName(),
                client.getLastname(),
                client.getPhone(),
                client.getDni(),
                client.getRating(),
                client.getUser(),
                client.getDirection()
        );
    }
}
