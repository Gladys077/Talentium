package cohorte16.homeservice.services;

import cohorte16.homeservice.dtos.ClientDTO;
import cohorte16.homeservice.dtos.ClientPutDTO;
import cohorte16.homeservice.dtos.ClientResponseDTO;

import java.util.List;

public interface ClientService {
    ClientResponseDTO save(ClientDTO clientDTO) throws Exception;
    ClientResponseDTO findById(Long id) throws Exception;
    List<ClientResponseDTO> findAll() throws  Exception;
    ClientResponseDTO update(Long id,ClientPutDTO clientDTO) throws Exception;
    boolean delete(Long id) throws Exception;
}
