package cohorte16.homeservice.models;

import cohorte16.homeservice.dtos.RegistroUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email",nullable = false, unique = true )
    private String email;
    @Column(name = "contrasenia", nullable = false)
    private String password;
    @Column(name = "avatar")
    private String avatar;

    public User(RegistroUsuarioDTO registroUsuarioDTO) {
        this.email = registroUsuarioDTO.email();
        this.password = registroUsuarioDTO.password();
        this.avatar = registroUsuarioDTO.avatar();
    }

    public User(Long userId, String avatar) {
        this.id = userId;
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}