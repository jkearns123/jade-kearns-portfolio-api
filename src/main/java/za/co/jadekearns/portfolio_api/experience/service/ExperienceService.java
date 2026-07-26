package za.co.jadekearns.portfolio_api.experience.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.experience.domain.Experience;
import za.co.jadekearns.portfolio_api.experience.dto.ExperienceResponse;
import za.co.jadekearns.portfolio_api.experience.repository.ExperienceRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public ExperienceService(
            ExperienceRepository experienceRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.experienceRepository = experienceRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<ExperienceResponse> getExperience() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return experienceRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ExperienceResponse toResponse(Experience experience) {
        return new ExperienceResponse(
                experience.getPublicId(),
                experience.getJobTitle(),
                experience.getCompanyName(),
                experience.getLocation(),
                experience.getStartYear(),
                experience.getStartMonth(),
                experience.getEndYear(),
                experience.getEndMonth(),
                experience.isCurrentPosition(),
                experience.getDescription(),
                experience.getDisplayOrder()
        );
    }
}
