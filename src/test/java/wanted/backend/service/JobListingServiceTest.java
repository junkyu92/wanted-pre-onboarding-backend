package wanted.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import wanted.backend.domain.Company;
import wanted.backend.domain.JobListing;
import wanted.backend.dto.JobListingDetailResponseDto;
import wanted.backend.dto.JobListingRequestDto;
import wanted.backend.repository.CompanyRepository;
import wanted.backend.repository.JobListingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobListingServiceTest {

    @InjectMocks private JobListingService jobListingService;

    @Mock private JobListingRepository jobListingRepository;
    @Mock private JobListingQueryRepository jobListingQueryRepository;
    @Mock private CompanyRepository companyRepository;

    @Test
    public void 채용공고_등록() {
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .region("판교")
                .build();
        JobListingRequestDto jobListingRequestDto = JobListingRequestDto.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        ResponseEntity<String> jobListing = jobListingService.createJobListing(jobListingRequestDto);

        assertThat(jobListing.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(jobListing.getBody()).isEqualTo("채용공고가 등록되었습니다.");

        verify(jobListingRepository, times(1)).save(any(JobListing.class));
    }

    @Test
    public void 채용공고_등록_실패() {
        JobListingRequestDto jobListingRequestDto = JobListingRequestDto.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();

        ResponseEntity<String> jobListing = jobListingService.createJobListing(jobListingRequestDto);

        assertThat(jobListing.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        assertThat(jobListing.getBody()).isEqualTo("회사ID "+jobListingRequestDto.getCompanyId()+"을 찾을 수 없습니다.");
    }

    @Test
    public void 채용공고_수정() {
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .region("판교")
                .build();
        JobListing jobListing = JobListing.builder()
                .company(company)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
        JobListingRequestDto jobListingRequestDto = JobListingRequestDto.builder()
                .jobListingId(1L)
                .position("백엔드 시니어 개발자")
                .reward(5000000L)
                .description("백엔드 시니어 채용")
                .techStack("python")
                .build();

        when(jobListingRepository.findById(1L)).thenReturn(Optional.of(jobListing));

        ResponseEntity<String> result = jobListingService.updateJobListing(jobListingRequestDto);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo("채용공고가 수정되었습니다.");
    }

    @Test
    public void 채용공고_수정_실패() {
        JobListingRequestDto jobListingRequestDto = JobListingRequestDto.builder()
                .jobListingId(1L)
                .position("백엔드 시니어 개발자")
                .reward(5000000L)
                .description("백엔드 시니어 채용")
                .techStack("python")
                .build();

        ResponseEntity<String> result = jobListingService.updateJobListing(jobListingRequestDto);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        assertThat(result.getBody()).isEqualTo("채용공고Id "+jobListingRequestDto.getJobListingId()+"을 찾을 수 없습니다.");
    }

    @Test
    public void 채용공고_삭제() {

        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .region("판교")
                .build();
        JobListing jobListing = JobListing.builder()
                .company(company)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();

        when(jobListingRepository.findById(1L)).thenReturn(Optional.of(jobListing));

        ResponseEntity<String> result = jobListingService.deleteJobListing(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo("채용공고가 삭제되었습니다.");
    }

    @Test
    public void 채용공고_삭제_실패() {
        ResponseEntity<String> result = jobListingService.deleteJobListing(99999L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        assertThat(result.getBody()).isEqualTo("채용공고Id "+99999L+"을 찾을 수 없습니다.");
    }

    @Test
    public void 채용상세_페이지() {
        List<JobListing> list = new ArrayList<>();
        list.add(JobListing.builder()
                .id(1L)
                .build());
        list.add(JobListing.builder()
                .id(2L)
                .build());
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .region("판교")
                .jobListings(list)
                .build();
        JobListing jobListing = JobListing.builder()
                .id(1L)
                .company(company)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();

        when(jobListingQueryRepository.findDetailById(1L)).thenReturn(jobListing);

        JobListingDetailResponseDto result = jobListingService.jobListing(1L);

        assertThat(result.getJobListingId()).isEqualTo(1L);
        assertThat(result.getPosition()).isEqualTo("백엔드 주니어 개발자");
        assertThat(result.getReward()).isEqualTo(1000000L);
        assertThat(result.getDescription()).isEqualTo("백엔드 개발자 채용");
        assertThat(result.getTechStack()).isEqualTo("spring");
        assertThat(result.getOtherJobListing()).isEqualTo(longList);
    }
}