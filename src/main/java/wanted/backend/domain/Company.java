package wanted.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.cert.CertPathBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String region;

    @OneToMany(mappedBy = "company")
    private List<JobListing> jobListings = new ArrayList<>();

    @Builder
    public Company(Long id, String name, String country, String region, List<JobListing> jobListings) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.jobListings = jobListings;
    }
}
