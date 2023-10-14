package wanted.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import wanted.backend.domain.Apply;
import wanted.backend.domain.Company;
import wanted.backend.domain.JobListing;
import wanted.backend.domain.User;
import wanted.backend.dto.ApplyRequestDto;
import wanted.backend.repository.ApplyRepository;
import wanted.backend.repository.JobListingRepository;
import wanted.backend.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplyServiceTest {

    @Mock private ApplyRepository applyRepository;
    @Mock private UserRepository userRepository;
    @Mock private JobListingRepository jobListingRepository;

    @InjectMocks private ApplyService applyService;

    @Test
    void 채용공고_지원(){
        User user = User.builder().name("이준규").build();
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .region("판교")
                .build();
        JobListing jobListing = JobListing.builder()
                        .company(company)
                        .position("백엔드 주니어 개발자")
                        .reward(1000000L)
                        .description("백엔드 주니어 채용")
                        .techStack("spring")
                .build();
        ApplyRequestDto applyRequestDto = new ApplyRequestDto();
        applyRequestDto.setUserId(1L);
        applyRequestDto.setJobListingId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(jobListingRepository.findById(1L)).thenReturn(Optional.of(jobListing));

        ResponseEntity<String> applyResult = applyService.createApply(applyRequestDto);

        assertThat(applyResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(applyResult.getBody()).isEqualTo("지원이 성공적으로 처리되었습니다.");

        verify(applyRepository, times(1)).save(any(Apply.class));
    }

    @Test
    public void 채용공고_지원_실패() {
        ApplyRequestDto applyRequestDto = new ApplyRequestDto();
        applyRequestDto.setJobListingId(1L);
        applyRequestDto.setUserId(2L);

        when(jobListingRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<String> applyResult = applyService.createApply(applyRequestDto);

        assertThat(applyResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        assertThat(applyResult.getBody()).isEqualTo("지원에 실패하였습니다.");

        verify(applyRepository, never()).save(any(Apply.class));
    }
}