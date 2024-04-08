package cohorte16.homeservice.mappers;

import cohorte16.homeservice.dtos.ClientDTO;
import cohorte16.homeservice.dtos.ClientResponseDTO;
import cohorte16.homeservice.dtos.DirectionDTO;
import cohorte16.homeservice.dtos.UserDTO;
import cohorte16.homeservice.models.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public ClientDTO clientToClientDTO(Client client)
    {
        return new ClientDTO(client);
    }

    public Client clientDTOToClient(ClientDTO clientDTO){
        return  new Client(

               null,
                clientDTO.name(),
                clientDTO.lastname(),
                clientDTO.phone(),
                clientDTO.dni(),
                clientDTO.user(),
                clientDTO.direction(),
                clientDTO.rating(),
                true,
                null
               );

    }

    public ClientResponseDTO clientToClientResponseDTO(Client client){
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getLastname(),
                client.getPhone(),
                client.getDni(),
                client.getRating(),
                new UserDTO(client.getUser().getId(),
                        client.getUser().getEmail(),
                        client.getUser().getAvatar()),
                new DirectionDTO(client.getDirection().getStreet(),
                        client.getDirection().getNumber(),
                        client.getDirection().getProvince(),
                        client.getDirection().getLocation())
        );
    }
}
