package wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
