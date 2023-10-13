package wanted.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import wanted.backend.domain.Company;

@Getter
@Setter
public class JobListingRequestDto {
    private Long jobListingId;
    private Long companyId;
    private String position;
    private Long reward;
    private String description;
    private String techStack;

}
