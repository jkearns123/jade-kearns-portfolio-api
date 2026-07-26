package za.co.jadekearns.portfolio_api.professionalreference.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.professionalreference.domain.ProfessionalReference;
import za.co.jadekearns.portfolio_api.professionalreference.dto.ProfessionalReferenceResponse;
import za.co.jadekearns.portfolio_api.professionalreference.repository.ProfessionalReferenceRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProfessionalReferenceService {
    private final ProfessionalReferenceRepository professionalReferenceRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public ProfessionalReferenceService(
            ProfessionalReferenceRepository professionalReferenceRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.professionalReferenceRepository = professionalReferenceRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<ProfessionalReferenceResponse> getProfessionalReferences() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return professionalReferenceRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProfessionalReferenceResponse toResponse(
            ProfessionalReference professionalReference
    ) {
        return new ProfessionalReferenceResponse(
                professionalReference.getPublicId(),
                professionalReference.getFullName(),
                professionalReference.getJobTitle(),
                professionalReference.getCompanyName(),
                professionalReference.getRelationship(),
                professionalReference.getContactDetailsNote(),
                professionalReference.getDisplayOrder()
        );
    }
}
