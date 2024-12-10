package adiq.securityextrasession.repo;

import adiq.securityextrasession.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

@Query("select u from User u where u.email like :email")
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
