package wanted.backend.service;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.domain.Apply;
import wanted.backend.domain.JobListing;
import wanted.backend.domain.User;
import wanted.backend.dto.ApplyRequestDto;
import wanted.backend.repository.ApplyRepository;
import wanted.backend.repository.JobListingRepository;
import wanted.backend.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final JobListingRepository jobListingRepository;

    //1. 채용공고 등록
    public ResponseEntity<String> createApply(ApplyRequestDto applyRequestDto) {
        Optional<JobListing> findJobListing = jobListingRepository.findById(applyRequestDto.getJobListingId());
        Optional<User> findUser = userRepository.findById(applyRequestDto.getUserId());

        if(findJobListing.isPresent() && findUser.isPresent()){
            applyRepository.save(Apply.builder()
                    .jobListing(findJobListing.get())
                    .user(findUser.get())
                    .build());
            return ResponseEntity.ok("지원이 성공적으로 처리되었습니다.");
        }else{
            return ResponseEntity.badRequest().body("지원에 실패하였습니다.");
        }

    }

}
