package wanted.backend.service;

import org.springframework.http.ResponseEntity;
import wanted.backend.domain.Company;
import wanted.backend.domain.JobListing;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.dto.JobListingDetailResponseDto;
import wanted.backend.dto.JobListingRequestDto;
import wanted.backend.dto.JobListingResponseDto;
import wanted.backend.repository.CompanyRepository;
import wanted.backend.repository.JobListingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobListingService {

    private final JobListingRepository jobListingRepository;
    private final JobListingQueryRepository jobListingQueryRepository;
    private final CompanyRepository companyRepository;

    //1. 채용공고 등록
    public ResponseEntity<String> createJobListing(JobListingRequestDto jobListingDto) {
        Optional<Company> findCompany = companyRepository.findById(jobListingDto.getCompanyId());
        if(findCompany.isPresent()){
            jobListingRepository.save(JobListing.builder()
                            .company(findCompany.get())
                            .position(jobListingDto.getPosition())
                            .reward(jobListingDto.getReward())
                            .description(jobListingDto.getDescription())
                            .techStack(jobListingDto.getTechStack())
                    .build());
            return ResponseEntity.ok("채용공고가 등록되었습니다.");
        }else{
            return ResponseEntity.badRequest().body("회사ID "+jobListingDto.getCompanyId()+"을 찾을 수 없습니다.");
        }
    }

    //2. 채용공고 수정
    public ResponseEntity<String> updateJobListing(JobListingRequestDto jobListingDto) {
        Optional<JobListing> jobListing = jobListingRepository.findById(jobListingDto.getJobListingId());
        if(jobListing.isPresent()){
            jobListing.get().updateJobListing(jobListingDto);
            return ResponseEntity.ok("채용공고가 수정되었습니다.");
        }else {
            return ResponseEntity.badRequest().body("채용공고Id "+jobListingDto.getJobListingId()+"을 찾을 수 없습니다.");
        }
    }

    //3. 채용공고 삭제
    public ResponseEntity<String> deleteJobListing(Long id) {
        jobListingRepository.deleteById(id);
        return ResponseEntity.ok("채용공고가 삭제되었습니다.");
    }

    //4-1. 채용공고 목록 조회
    @Transactional(readOnly = true)
    public Page<JobListingResponseDto> jobListingList(Pageable pageable) {
        return jobListingQueryRepository.findAll(pageable);
    }

    //4-2. 채용공고 검색
    @Transactional(readOnly = true)
    public Page<JobListingResponseDto> jobListingSearch(String searchCondition, Pageable pageable) {
        return jobListingQueryRepository.findByCondition(searchCondition, pageable);
    }

    //5. 채용 상세 페이지
    @Transactional(readOnly = true)
    public JobListingDetailResponseDto jobListing(Long id) {
        JobListing jobListings = jobListingQueryRepository.findDetailById(id);
        return JobListingDetailResponseDto.builder()
                .jobListingId(jobListings.getId())
                .name(jobListings.getCompany().getName())
                .country(jobListings.getCompany().getCountry())
                .region(jobListings.getCompany().getRegion())
                .position(jobListings.getPosition())
                .reward(jobListings.getReward())
                .techStack(jobListings.getTechStack())
                .description(jobListings.getDescription())
                .otherJobListing(jobListings.getCompany().getJobListings().stream().map(JobListing::getId).collect(Collectors.toList()))
                .build();
    }
}
