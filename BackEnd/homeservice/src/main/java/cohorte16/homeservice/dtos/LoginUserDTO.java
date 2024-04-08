package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.User;
import jakarta.validation.constraints.NotBlank;

public class LoginUserDTO {
    public static User LoginUserDTO(User user) {
      return new User(user.getId(), user.getEmail(),null,null);
    }
}
