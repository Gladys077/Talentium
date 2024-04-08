package cohorte16.homeservice.services.impl;

import cohorte16.homeservice.dtos.ClientDTO;
import cohorte16.homeservice.dtos.ClientPutDTO;
import cohorte16.homeservice.dtos.ClientResponseDTO;
import cohorte16.homeservice.mappers.ClientMapper;
import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Direction;
import cohorte16.homeservice.models.User;
import cohorte16.homeservice.repositories.ClientRepository;
import cohorte16.homeservice.repositories.UserRepository;
import cohorte16.homeservice.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    UserRepository userRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDTO save(ClientDTO clientDTO) throws Exception {
        try {
            Client clientEntity = clientMapper.clientDTOToClient(clientDTO);
            Client clientSaved = clientRepository.save(clientEntity);
            return clientMapper.clientToClientResponseDTO(clientSaved);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClientResponseDTO findById(Long id) throws Exception {
        try {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if (clientOptional.isEmpty()) {
                throw new EntityNotFoundException("Client not found");
            }
            Client client = clientOptional.get();
            return clientMapper.clientToClientResponseDTO(client);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ClientResponseDTO> findAll() throws Exception {
        try {
            List<Client> clientList = clientRepository.findAll();
            return clientList.stream().map(clientMapper::clientToClientResponseDTO).toList();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClientResponseDTO update(Long id, ClientPutDTO clientDTO) {
        try {
            Client clientExisting = clientRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
            Client clientEntity = updateClientFromClientDTO(clientExisting,clientDTO);
            Client savedClient = clientRepository.save(clientEntity);
            return clientMapper.clientToClientResponseDTO(savedClient);
        }catch (EntityNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException("Error ocurred while updating client with id " + id, e);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if(clientRepository.existsById(id)){
                Client client = clientRepository.findById(id).get();
                client.setActive(false);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private static Client updateClientFromClientDTO(Client clientExisting,
                                                    ClientPutDTO clientDTO) {
        clientExisting.setPhone(clientDTO.phone());
        clientExisting.setDni(clientDTO.dni());
        clientExisting.setDirection(
                new Direction(
                        null,
                        clientDTO.direction().number(),
                        clientDTO.direction().street(),
                        clientDTO.direction().province(),
                        clientDTO.direction().location())
        );
        return clientExisting;
    }
}
