package wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import wanted.backend.dto.ApplyRequestDto;
import wanted.backend.dto.JobListingDetailResponseDto;
import wanted.backend.dto.JobListingRequestDto;
import wanted.backend.dto.JobListingResponseDto;
import wanted.backend.service.ApplyService;
import wanted.backend.service.JobListingService;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApplyService applyService;
    private final JobListingService jobListingService;

    //1. 채용공고 등록
    @PostMapping("/api/v1/job-listing")
    public ResponseEntity<String> createJobListing(@RequestBody JobListingRequestDto jobListingDto) {
        return jobListingService.createJobListing(jobListingDto);
    }

    //2. 채용공고 수정
    @PatchMapping("/api/v1/job-listing")
    public ResponseEntity<String> updateJobListing(@RequestBody JobListingRequestDto jobListingDto) {
        return jobListingService.updateJobListing(jobListingDto);
    }

    //3. 채용공고 삭제
    @DeleteMapping("/api/v1/job-listing/{id}")
    public ResponseEntity<String> deleteJobListing(@PathVariable("id") Long id) {
        return jobListingService.deleteJobListing(id);
    }

    //4-1. 채용공고 목록 조회
    @GetMapping("/api/v1/job-listings")
    public Page<JobListingResponseDto> jobListingList(Pageable pageable) {
        return jobListingService.jobListingList(pageable);
    }

    //4-2. 채용공고 검색
    @GetMapping("/api/v1/job-listings/search")
    public Page<JobListingResponseDto> jobListingSearch(@RequestParam("keyword") String keyword, Pageable pageable) {
        return jobListingService.jobListingSearch(keyword, pageable);
    }

    //5. 채용 상세 페이지
    @GetMapping("/api/v1/job-listing/{id}")
    public JobListingDetailResponseDto jobListing(@PathVariable("id") Long id) {
        return jobListingService.jobListing(id);
    }

    //6. 채용공고 지원
    @PostMapping("/api/v1/apply")
    public ResponseEntity<String> apply(@RequestBody ApplyRequestDto applyRequestDto) {
        return applyService.createApply(applyRequestDto);
    }
}
