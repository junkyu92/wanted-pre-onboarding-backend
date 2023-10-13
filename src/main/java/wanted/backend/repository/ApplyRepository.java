package wanted.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.backend.domain.Apply;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
