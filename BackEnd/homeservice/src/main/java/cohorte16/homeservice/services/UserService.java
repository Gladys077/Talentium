package cohorte16.homeservice.services;

import cohorte16.homeservice.dtos.LoginDTO;
import cohorte16.homeservice.dtos.RegistroUsuarioDTO;
import cohorte16.homeservice.models.User;

public interface UserService {

    public User saveUser(RegistroUsuarioDTO registroUsuarioDTO);

    public User validateLogin(LoginDTO datosLogin);
}
