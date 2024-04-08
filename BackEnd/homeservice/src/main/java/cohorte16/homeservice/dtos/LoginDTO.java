package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.User;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(Long id,@NotBlank String email, @NotBlank String password, String avatar) {

    public LoginDTO(User user) {

        this(user.getId(),user.getEmail(),null,user.getAvatar());
    }


}
