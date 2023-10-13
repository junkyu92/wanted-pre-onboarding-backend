package wanted.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
