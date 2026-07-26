package za.co.jadekearns.portfolio_api.professionalreference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.professionalreference.domain.ProfessionalReference;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalReferenceRepository extends JpaRepository<ProfessionalReference, Long> {
    Optional<ProfessionalReference> findByPublicId(UUID publicId);

    List<ProfessionalReference> findAllByProfile_IdOrderByDisplayOrderAsc(
            Long profileId
    );

    List<ProfessionalReference> findAllByProfile_PublicIdOrderByDisplayOrderAsc(
            UUID profilePublicId
    );
}
