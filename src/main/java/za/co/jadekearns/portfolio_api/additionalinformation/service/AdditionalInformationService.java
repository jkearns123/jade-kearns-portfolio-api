package za.co.jadekearns.portfolio_api.additionalinformation.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.additionalinformation.domain.AdditionalInformation;
import za.co.jadekearns.portfolio_api.additionalinformation.dto.AdditionalInformationResponse;
import za.co.jadekearns.portfolio_api.additionalinformation.repository.AdditionalInformationRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdditionalInformationService {
    private final AdditionalInformationRepository additionalInformationRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public AdditionalInformationService(
            AdditionalInformationRepository additionalInformationRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.additionalInformationRepository = additionalInformationRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<AdditionalInformationResponse> getAdditionalInformation() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return additionalInformationRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AdditionalInformationResponse toResponse(
            AdditionalInformation additionalInformation
    ) {
        return new AdditionalInformationResponse(
                additionalInformation.getPublicId(),
                additionalInformation.getInformationType(),
                additionalInformation.getValue(),
                additionalInformation.getDisplayOrder()
        );
    }
}
