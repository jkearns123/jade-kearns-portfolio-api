package za.co.jadekearns.portfolio_api.experience.dto;

import java.util.UUID;

public record ExperienceResponse(
        UUID publicId,
        String jobTitle,
        String companyName,
        String location,
        Short startYear,
        Short startMonth,
        Short endYear,
        Short endMonth,
        boolean currentPosition,
        String description,
        int displayOrder
) {
}
