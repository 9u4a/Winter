package study.security.security.common.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.security.common.auth.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
