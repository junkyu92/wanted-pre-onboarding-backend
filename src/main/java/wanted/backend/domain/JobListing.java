package wanted.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.backend.dto.JobListingRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobListing {
    @Id @Column(name = "job_listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String position;
    private Long reward;

    @Column(length = 1000)
    private String description;

    private String techStack;
    @Builder
    public JobListing(Long id, Company company, String position, Long reward, String description, String techStack) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.techStack = techStack;
    }

    public void updateJobListing(JobListingRequestDto jobListingRequestDto){
        if(jobListingRequestDto.getPosition()!=null) this.position = jobListingRequestDto.getPosition();
        if(jobListingRequestDto.getReward()!=null) this.reward = jobListingRequestDto.getReward();
        if(jobListingRequestDto.getDescription()!=null) this.description = jobListingRequestDto.getDescription();
        if(jobListingRequestDto.getTechStack()!=null) this.techStack = jobListingRequestDto.getTechStack();
    }
}
