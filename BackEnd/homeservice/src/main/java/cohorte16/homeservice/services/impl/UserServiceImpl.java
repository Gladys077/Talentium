package cohorte16.homeservice.services.impl;

import cohorte16.homeservice.dtos.LoginDTO;
import cohorte16.homeservice.dtos.RegistroUsuarioDTO;
import cohorte16.homeservice.models.User;
import cohorte16.homeservice.repositories.UserRepository;
import cohorte16.homeservice.security.EncryptData;
import cohorte16.homeservice.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(RegistroUsuarioDTO registroUsuarioDTO) {
       return userRepository.save(
               new User(null,
                       registroUsuarioDTO.email(),
                       EncryptData.encryptPassword(registroUsuarioDTO.password()),
                       registroUsuarioDTO.avatar()
               )
       );
    }

    @Override
    public User validateLogin(LoginDTO datosLogin) {
       return userRepository.findByEmailAndContrasenia(datosLogin.email(),
                EncryptData.encryptPassword(datosLogin.password())
        );
    }
}

