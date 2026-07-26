package za.co.jadekearns.portfolio_api.profile.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.dto.PortfolioProfileResponse;
import za.co.jadekearns.portfolio_api.profile.dto.ProfileImage;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;

@Service
@Transactional(readOnly = true)
public class PortfolioProfileService {
    private static final String PROFILE_IMAGE_ENDPOINT = "/api/profile/image";

    private final PortfolioProfileRepository portfolioProfileRepository;

    public PortfolioProfileService(
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public PortfolioProfileResponse getProfile() {
        PortfolioProfile profile = getPortfolioProfile();
        boolean hasProfileImage = profile.getProfileImageData() != null
                && profile.getProfileImageData().length > 0;

        return new PortfolioProfileResponse(
                profile.getPublicId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getFirstName() + " " + profile.getLastName(),
                profile.getProfessionalTitle(),
                profile.getHeadline(),
                profile.getProfessionalSummary(),
                profile.isShowEmail() ? profile.getEmail() : null,
                profile.isShowMobileNumber()
                        ? profile.getMobileNumber()
                        : null,
                profile.getCity(),
                profile.getProvince(),
                profile.getCountry(),
                profile.isAvailableForWork(),
                hasProfileImage ? PROFILE_IMAGE_ENDPOINT : null,
                profile.getResumeUrl()
        );
    }

    public ProfileImage getProfileImage() {
        PortfolioProfile profile = getPortfolioProfile();
        byte[] imageData = profile.getProfileImageData();

        if (imageData == null || imageData.length == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Profile image not found"
            );
        }

        String fileName = profile.getProfileImageFileName() == null
                ? "profile-image"
                : profile.getProfileImageFileName();

        String contentType = profile.getProfileImageContentType() == null
                ? "application/octet-stream"
                : profile.getProfileImageContentType();

        return new ProfileImage(fileName, contentType, imageData);
    }

    private PortfolioProfile getPortfolioProfile() {
        return portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));
    }
}
