package za.co.jadekearns.portfolio_api.education.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.education.domain.Education;
import za.co.jadekearns.portfolio_api.education.dto.EducationResponse;
import za.co.jadekearns.portfolio_api.education.repository.EducationRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EducationService {
    private final EducationRepository educationRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public EducationService(
            EducationRepository educationRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.educationRepository = educationRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<EducationResponse> getEducation() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return educationRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private EducationResponse toResponse(Education education) {
        return new EducationResponse(
                education.getPublicId(),
                education.getInstitution(),
                education.getQualification(),
                education.getFieldOfStudy(),
                education.getLocation(),
                education.getStartYear(),
                education.getEndYear(),
                education.isCurrentStudy(),
                education.getDescription(),
                education.getDisplayOrder()
        );
    }
}
