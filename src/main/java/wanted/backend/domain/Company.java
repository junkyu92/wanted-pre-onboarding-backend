package wanted.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Company {
    @Id @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String region;

    @OneToMany(mappedBy = "company")
    private List<JobListing> jobListings = new ArrayList<>();
}
