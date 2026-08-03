package za.co.jadekearns.portfolio_api.profile.dto;

import java.util.UUID;

public record PortfolioProfileResponse(
        UUID publicId,
        String firstName,
        String lastName,
        String fullName,
        String professionalTitle,
        String headline,
        String professionalSummary,
        String email,
        String mobileNumber,
        String city,
        String province,
        String country,
        boolean availableForWork,
        String resumeUrl,
        String linkedInUrl,
        String githubUrl
) {
}
