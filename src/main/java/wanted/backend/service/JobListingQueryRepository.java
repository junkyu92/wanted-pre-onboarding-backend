package wanted.backend.service;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import wanted.backend.domain.JobListing;
import wanted.backend.domain.QJobListing;
import wanted.backend.dto.JobListingDetailResponseDto;
import wanted.backend.dto.JobListingResponseDto;
import wanted.backend.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static wanted.backend.domain.QCompany.*;
import static wanted.backend.domain.QJobListing.*;

@Repository
public class JobListingQueryRepository extends QuerydslRepositorySupport {
    public JobListingQueryRepository() {
        super(JobListing.class);
    }

    public Page<JobListingResponseDto> findAll(Pageable pageable) {
        return applyPagination(pageable, query -> query
                .select(Projections.constructor(JobListingResponseDto.class,
                        jobListing.id,
                        company.name,
                        company.country,
                        company.region,
                        jobListing.position,
                        jobListing.reward,
                        jobListing.techStack
                        ))
                .from(jobListing)
                .leftJoin(jobListing.company, company)
                );
    }

    public Page<JobListingResponseDto> findByCondition(String searchCondition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .select(Projections.constructor(JobListingResponseDto.class,
                        jobListing.id,
                        company.name,
                        company.country,
                        company.region,
                        jobListing.position,
                        jobListing.reward,
                        jobListing.techStack
                ))
                .from(jobListing)
                .leftJoin(jobListing.company, company)
                .where(company.name.contains(searchCondition).or(jobListing.position.contains(searchCondition)))
        );
    }

    public JobListing findDetailById(Long id) {
        QJobListing qJobListing = new QJobListing("new");
        return getQueryFactory()
                .selectFrom(jobListing)
                .leftJoin(jobListing.company, company).fetchJoin()
                .leftJoin(jobListing.company.jobListings, qJobListing).fetchJoin()
                .where(jobListing.id.eq(id))
                .fetchOne();
    }
}
