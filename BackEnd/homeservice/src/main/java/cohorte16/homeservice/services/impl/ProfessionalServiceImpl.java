package cohorte16.homeservice.services.impl;

import cohorte16.homeservice.dtos.ProfessionalDTO;
import cohorte16.homeservice.dtos.ProfessionalPutDTO;
import cohorte16.homeservice.dtos.ProfessionalResponseDTO;
import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.mappers.ProfessionalMapper;
import cohorte16.homeservice.models.Direction;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.models.User;
import cohorte16.homeservice.repositories.ProfessionalRepository;
import cohorte16.homeservice.repositories.UserRepository;
import cohorte16.homeservice.services.ProfessionalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ProfessionalMapper professionalMapper;
    private final UserRepository userRepository;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository,
                                   UserRepository userRepository,
                                   ProfessionalMapper professionalMapper){
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
        this.professionalMapper = professionalMapper;
    }

    @Override
    public List<ProfessionalResponseDTO> findAll() {
        List<ProfessionalResponseDTO> professionalDTOs;
        try {
            List<Professional> professionalList = professionalRepository.findAll();
            professionalDTOs = professionalList.stream()
                       .map(professionalMapper::professionalToProfessionalResponseDTO)
                       .toList();
           }catch (Exception e){
                throw new ServiceException("Error occurred while fetching all professionals", e);
        }
        return professionalDTOs;
    }

    @Override
    public ProfessionalResponseDTO findById(Long id) {
        try {
            Professional professional = professionalRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Professional not found with id: " + id));
            return professionalMapper.professionalToProfessionalResponseDTO(professional);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error occurred while fetching professional with id: " + id, e);
        }
    }

    @Override
    public ProfessionalResponseDTO findByUser(Long id) {
        try{
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
            Professional professional = professionalRepository.findProfessionalByUser(user);
            return professionalMapper.professionalToProfessionalResponseDTO(professional);
        }catch (EntityNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException("Error ocurred while finding professional with id " + id,e);
        }
    }

    @Override
    public List<ProfessionalResponseDTO> findByProfession(Profession profession) {
        List<ProfessionalResponseDTO> professionalDTOs;
        try {
            List<Professional> professionalList = professionalRepository.findProfessionalByProfession(profession);
            professionalDTOs = professionalList.stream()
                    .map(professionalMapper::professionalToProfessionalResponseDTO)
                    .toList();
        }catch (Exception e){
            throw new ServiceException("Error occurred while fetching all professionals", e);
        }
        return professionalDTOs;
    }

    @Override
    public ProfessionalResponseDTO save(ProfessionalDTO professionalDTO) {
        try {
            Professional professionalEntity = professionalMapper.professionalDTOToProfessional(professionalDTO);
            User userProfessional = userRepository.findById(professionalDTO.user().getId())
                    .orElseThrow(()-> new EntityNotFoundException("User not found"));
            professionalEntity.setUser(userProfessional);
            Professional professionalSaved = professionalRepository.save(professionalEntity);
            return professionalMapper.professionalToProfessionalResponseDTO(professionalSaved);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error occurred while saving professional", e);
        }
    }

    @Override
    public ProfessionalResponseDTO update(Long id, ProfessionalPutDTO professionalDTO) {
        try {
            Professional existingProfessional = professionalRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("Professional not found"));
            Professional updatedProfessional = updateProfessionalFromDTO(existingProfessional, professionalDTO);
            Professional savedProfessional = professionalRepository.save(updatedProfessional);
            return professionalMapper.professionalToProfessionalResponseDTO(savedProfessional);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error occurred while updating professional with id: " + id, e);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if(professionalRepository.existsById(id)){
                Professional professional = professionalRepository.findById(id).get();
                professional.setActive(Boolean.FALSE);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Professional updateProfessionalFromDTO(Professional existingProfessional,
                                                  ProfessionalPutDTO professionalDTO) {


        existingProfessional.setCuit(professionalDTO.cuit());
        existingProfessional.setCbu(professionalDTO.cbu());

        existingProfessional.setProfession(professionalDTO.profession());
        existingProfessional.setDirection(new Direction(
                existingProfessional.getDirection().getId(),
                professionalDTO.direction().street(),
                professionalDTO.direction().number(),
                professionalDTO.direction().province(),
                professionalDTO.direction().location()
        ));

        return existingProfessional;
    }
}
