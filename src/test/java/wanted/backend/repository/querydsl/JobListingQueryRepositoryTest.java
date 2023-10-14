package wanted.backend.repository.querydsl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import wanted.backend.domain.Company;
import wanted.backend.domain.JobListing;
import wanted.backend.dto.JobListingResponseDto;
import wanted.backend.repository.CompanyRepository;
import wanted.backend.repository.JobListingRepository;
import wanted.backend.service.JobListingQueryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@TestConfiguration
class config {
    @Bean
    public JobListingQueryRepository jobListingQueryRepository() {
        return new JobListingQueryRepository();
    }
}
@DataJpaTest
@Import(config.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobListingQueryRepositoryTest {

    @Autowired private JobListingQueryRepository jobListingQueryRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private JobListingRepository jobListingRepository;

    @BeforeEach
    void before() {
        Company company = companyRepository.save(Company.builder()
                .name("테스트1")
                .country("한국")
                .region("판교")
                .build());
        Company company2 = companyRepository.save(Company.builder()
                .name("테스트3")
                .country("한국")
                .region("판교")
                .build());
        JobListing jobListing1 = JobListing.builder()
                .company(company)
                .position("테스트3")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
        JobListing jobListing2 = JobListing.builder()
                .company(company)
                .position("테스트2")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
        JobListing jobListing3 = JobListing.builder()
                .company(company2)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .description("백엔드 개발자 채용")
                .techStack("spring")
                .build();
        List<JobListing> list = new ArrayList<>();
        list.add(jobListing1);
        list.add(jobListing2);
        list.add(jobListing3);
        jobListingRepository.saveAll(list);
    }

    @Test
    void 채용공고_리스트_조회(){
        Page<JobListingResponseDto> results = jobListingQueryRepository.findAll(PageRequest.of(0, 10));
        assertThat(results.getTotalElements()).isEqualTo(jobListingRepository.count());
    }

    @Test
    void 채용공고_리스트_검색(){
        //검색어x -> 전체조회
        Page<JobListingResponseDto> results = jobListingQueryRepository.findByCondition("", PageRequest.of(0, 10));
        assertThat(results.getTotalElements()).isEqualTo(jobListingRepository.count());

        //포지션에서 검색확인
        results = jobListingQueryRepository.findByCondition("테스트1", PageRequest.of(0, 10));
        assertThat(results.getTotalElements()).isEqualTo(2);

        //description에서 검색확인
        results = jobListingQueryRepository.findByCondition("테스트2", PageRequest.of(0, 10));
        assertThat(results.getTotalElements()).isEqualTo(1);

        //포지션, description에서 검색확인
        results = jobListingQueryRepository.findByCondition("테스트3", PageRequest.of(0, 10));
        assertThat(results.getTotalElements()).isEqualTo(2);
    }
}