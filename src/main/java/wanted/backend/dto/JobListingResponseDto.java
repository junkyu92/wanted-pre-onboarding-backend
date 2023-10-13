package wanted.backend.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobListingResponseDto {
    private Long jobListingId;
    private String name;
    private String country;
    private String region;
    private String position;
    private Long reward;
    private String techStack;

    public JobListingResponseDto(Long jobListingId, String name, String country, String region, String position, Long reward, String techStack) {
        this.jobListingId = jobListingId;
        this.name = name;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
    }
}
