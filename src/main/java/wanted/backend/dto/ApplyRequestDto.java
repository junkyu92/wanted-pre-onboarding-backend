package wanted.backend.dto;

import lombok.Getter;
import lombok.Setter;
import wanted.backend.domain.Apply;

@Getter
@Setter
public class ApplyRequestDto {
    private Long jobListingId;
    private Long userId;
}
