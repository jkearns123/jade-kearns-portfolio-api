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

import java.time.Year;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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

    public int getYearsOfExperience() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        List<Experience> experiences = experienceRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId());

        int earliestStartYear = experiences.stream()
                .filter(experience -> experience.getStartYear() != null)
                .mapToInt(experience -> experience.getStartYear())
                .distinct()
                .min()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No experience records found"
                ));

        return Math.max(
                0,
                Year.now().getValue() - earliestStartYear
        );
    }

    public int getSystemAnalystYearsOfExperience() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        List<Experience> experiences = experienceRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId());

        int currentYear = Year.now().getValue();

        return experiences.stream()
                .filter(experience -> experience.getJobTitle() != null)
                .filter(experience ->
                        experience.getJobTitle()
                                .toLowerCase(Locale.ROOT)
                                .contains("system analyst")
                )
                .filter(experience -> experience.getStartYear() != null)
                .mapToInt(experience -> {
                    int startYear = experience.getStartYear();

                    int endYear = experience.getEndYear() != null
                            ? experience.getEndYear()
                            : currentYear;

                    return Math.max(0, endYear - startYear);
                })
                .sum();
    }
}
