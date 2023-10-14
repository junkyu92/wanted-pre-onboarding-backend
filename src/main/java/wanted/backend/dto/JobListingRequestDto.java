package wanted.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import wanted.backend.domain.Company;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobListingRequestDto {
    private Long jobListingId;
    private Long companyId;
    private String position;
    private Long reward;
    private String description;
    private String techStack;

    @Builder
    public JobListingRequestDto(Long jobListingId, Long companyId, String position, Long reward, String description, String techStack) {
        this.jobListingId = jobListingId;
        this.companyId = companyId;
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.techStack = techStack;
    }
}
