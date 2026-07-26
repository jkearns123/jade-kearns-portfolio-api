package za.co.jadekearns.portfolio_api.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.interest.domain.Interest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findByPublicId(UUID publicId);

    List<Interest> findAllByProfile_IdOrderByDisplayOrderAsc(
            Long profileId
    );

    List<Interest> findAllByProfile_PublicIdOrderByDisplayOrderAsc(
            UUID profilePublicId
    );
}
