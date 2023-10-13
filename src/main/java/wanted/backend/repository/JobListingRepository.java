package wanted.backend.repository;

import wanted.backend.domain.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {


}
