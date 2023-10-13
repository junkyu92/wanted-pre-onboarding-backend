package wanted.backend.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.backend.domain.JobListing;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobListingDetailResponseDto {
    private Long jobListingId;
    private String name;
    private String country;
    private String region;
    private String position;
    private Long reward;
    private String techStack;
    private String description;
    private List<Long> otherJobListing;

    @Builder
    public JobListingDetailResponseDto(Long jobListingId, String name, String country, String region, String position, Long reward, String techStack, String description, List<Long> otherJobListing) {
        this.jobListingId = jobListingId;
        this.name = name;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
        this.description = description;
        this.otherJobListing = otherJobListing;
    }
}
