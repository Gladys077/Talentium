package cohorte16.homeservice.controllers;

import cohorte16.homeservice.dtos.LoginDTO;
import cohorte16.homeservice.dtos.ProfessionalDTO;
import cohorte16.homeservice.dtos.RegistroUsuarioDTO;
import cohorte16.homeservice.dtos.ClientUserDTO;
import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.models.User;
import cohorte16.homeservice.repositories.ClientRepository;
import cohorte16.homeservice.repositories.ProfessionalRepository;
import cohorte16.homeservice.security.EncryptData;
import cohorte16.homeservice.security.TokenService;
import cohorte16.homeservice.services.impl.UserServiceImpl;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


import static java.net.URI.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private ProfessionalRepository professionalRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    @PostMapping
    public ResponseEntity<?> RegistrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){

        Authentication authToken;
        Authentication usuarioAutenticado;
        User userCreated;
        User user = null;
        String JWToken;
        HttpHeaders jwtToken = new HttpHeaders();


        Client client;
        Professional professional;

        try {
          userCreated =  userServiceImpl.saveUser(registroUsuarioDTO);
            authToken = new UsernamePasswordAuthenticationToken(registroUsuarioDTO.email(), EncryptData.encryptPassword(registroUsuarioDTO.password() ));
            user = new User(userCreated.getId(),userCreated.getEmail(),userCreated.getPassword(),null);
            JWToken = tokenService.generarToken(user);
            jwtToken.set("Authorization",JWToken);

        }catch (Exception ex){

            return  ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        //devulve por convencion la url con los datos del usuario crado
        return ResponseEntity.created(create("/usuarios/"+new RegistroUsuarioDTO(userCreated).id())).headers(jwtToken)
                .body(RegistroUsuarioDTO.builder()
                .avatar(userCreated
                        .getAvatar())
                .email(userCreated.getEmail())
                        .password(JWToken)//agregando jwt
                .id(userCreated.getId()).build());
    }


    @PostMapping( value = "/login")
    public ResponseEntity <?> login(@RequestBody @Valid LoginDTO datosLogin) {;

        Authentication authToken;
        Authentication usuarioAutenticado;
        User userCreated;
        User user = null;
        String JWToken;
        HttpHeaders jwtToken = new HttpHeaders();


        Client client;
        Professional professional;

        try {
            userCreated = userServiceImpl.validateLogin(datosLogin);

           authToken = new UsernamePasswordAuthenticationToken(datosLogin.email(),  EncryptData.encryptPassword(datosLogin.password() ));
              user = new User(userCreated.getId(),userCreated.getEmail(),userCreated.getPassword(),userCreated.getAvatar());
              JWToken = tokenService.generarToken(user);
             jwtToken.set("Authorization",JWToken);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        if(userCreated != null) {
            client = clientRepository.findClienteByUser(userCreated);

            if(client != null){
                client.setLastname(JWToken);//agregando jwt
                return new ResponseEntity<>(new ClientUserDTO(client), jwtToken, HttpStatus.OK);
            }
        }

        if(userCreated != null) {
             professional= professionalRepository.findProfessionalByUser(userCreated);

            if(professional != null){
                 professional.setLastname(JWToken);//agregando jwt
                return new ResponseEntity<>(new ProfessionalDTO(professional), jwtToken, HttpStatus.OK);
            }
        }

        return ResponseEntity.created(create("/usuarios/login/"+new RegistroUsuarioDTO(userCreated).id())).headers(jwtToken)
                .body(RegistroUsuarioDTO.builder()
                .avatar(userCreated
                        .getAvatar())
                .email(userCreated.getEmail())
                        .password(JWToken)//agregando jwt
                .id(userCreated.getId()).build()
                            );

    }
}
