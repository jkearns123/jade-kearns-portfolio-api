package za.co.jadekearns.portfolio_api.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.education.domain.Education;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EducationRepository extends JpaRepository<Education, Long> {
    Optional<Education> findByPublicId(UUID publicId);
    List<Education> findAllByProfile_IdOrderByDisplayOrderAsc(Long profileId);
    List<Education> findAllByProfile_PublicIdOrderByDisplayOrderAsc(UUID profilePublicId);
}
