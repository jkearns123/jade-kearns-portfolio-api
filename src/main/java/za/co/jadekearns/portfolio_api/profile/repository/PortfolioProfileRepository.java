package za.co.jadekearns.portfolio_api.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;

import java.util.Optional;
import java.util.UUID;

public interface PortfolioProfileRepository extends JpaRepository<PortfolioProfile, Long> {
    Optional<PortfolioProfile> findByPublicId(UUID publicId);
    Optional<PortfolioProfile> findByEmail(String email);
    Optional<PortfolioProfile> findFirstByOrderByIdAsc();
}
