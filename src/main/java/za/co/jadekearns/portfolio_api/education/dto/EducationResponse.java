package za.co.jadekearns.portfolio_api.education.dto;

import java.util.UUID;

public record EducationResponse(
        UUID publicId,
        String institution,
        String qualification,
        String fieldOfStudy,
        String location,
        Short startYear,
        Short endYear,
        boolean currentStudy,
        String description,
        int displayOrder
) {
}
