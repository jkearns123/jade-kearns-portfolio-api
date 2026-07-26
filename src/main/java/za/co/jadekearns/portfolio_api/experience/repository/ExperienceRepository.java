package za.co.jadekearns.portfolio_api.experience.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.experience.domain.Experience;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    Optional<Experience> findByPublicId(UUID publicId);
    List<Experience> findAllByProfile_IdOrderByDisplayOrderAsc(Long profileId);
    List<Experience> findAllByProfile_PublicIdOrderByDisplayOrderAsc(UUID profilePublicId);
}
