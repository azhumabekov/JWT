package adiq.securityextrasession.entity;

import adiq.securityextrasession.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String username;
    String email;
    LocalDate createdAt;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращаем роли пользователя в виде списка прав доступа
        return List.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public String getUsername() {
        // Используется email как имя пользователя для аутентификации
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика проверки срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика проверки заблокирован ли аккаунт
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика проверки срока действия учетных данных
    }

    @Override
    public String getPassword() {
        return password; // Возвращаем пароль для аутентификации
    }

    @Override
    public boolean isEnabled() {
        return true; // Логика проверки активности пользователя
    }

    @PreUpdate
    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }

}
