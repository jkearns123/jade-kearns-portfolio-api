package za.co.jadekearns.portfolio_api.interest.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.interest.domain.Interest;
import za.co.jadekearns.portfolio_api.interest.dto.InterestResponse;
import za.co.jadekearns.portfolio_api.interest.repository.InterestRepository;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InterestService {
    private final InterestRepository interestRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public InterestService(
            InterestRepository interestRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.interestRepository = interestRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<InterestResponse> getInterests() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return interestRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InterestResponse toResponse(Interest interest) {
        return new InterestResponse(
                interest.getPublicId(),
                interest.getName(),
                interest.getDescription(),
                interest.getDisplayOrder()
        );
    }
}
