package cohorte16.homeservice.repositories;

import cohorte16.homeservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {

    @Query(value = "SELECT * FROM usuarios WHERE email = :email AND contrasenia = :password",nativeQuery = true)
    User findByEmailAndContrasenia(@Param("email") String email, @Param("password") String password);

    UserDetails findByEmail(String email);

  //  User findByLogin(String nombreUsuario);
}