package wanted.backend.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wanted.backend.dto.JobListingRequestDto;

import static org.junit.jupiter.api.Assertions.*;

class JobListingTest {
    JobListing jobListing = null;
    @BeforeEach
    void before() {
        jobListing = JobListing.builder()
                .company(Company.builder().build())
                .id(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
    }

    @Test
    void 채용공고_수정메서드_파라미터X(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("백엔드 주니어 개발자");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(1000000L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("백엔드 개발자 채용");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("spring");
    }

    @Test
    void 채용공고_수정메서드_position(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                .position("change")
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("change");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(1000000L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("백엔드 개발자 채용");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("spring");
    }

    @Test
    void 채용공고_수정메서드_reward(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                        .reward(100L)
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("백엔드 주니어 개발자");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(100L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("백엔드 개발자 채용");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("spring");
    }

    @Test
    void 채용공고_수정메서드_description(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                .description("change")
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("백엔드 주니어 개발자");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(1000000L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("change");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("spring");
    }

    @Test
    void 채용공고_수정메서드_techStack(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                .techStack("change")
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("백엔드 주니어 개발자");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(1000000L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("백엔드 개발자 채용");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("change");
    }

    @Test
    void 채용공고_수정메서드_All(){
        jobListing.updateJobListing(JobListingRequestDto.builder()
                .jobListingId(1L)
                .position("change1")
                .reward(2L)
                .description("change3")
                .techStack("change4")
                .build());
        Assertions.assertThat(jobListing.getPosition()).isEqualTo("change1");
        Assertions.assertThat(jobListing.getReward()).isEqualTo(2L);
        Assertions.assertThat(jobListing.getDescription()).isEqualTo("change3");
        Assertions.assertThat(jobListing.getTechStack()).isEqualTo("change4");
    }
}