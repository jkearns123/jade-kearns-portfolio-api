package za.co.jadekearns.portfolio_api.professionalreference.dto;

import java.util.UUID;

public record ProfessionalReferenceResponse(
        UUID publicId,
        String fullName,
        String jobTitle,
        String companyName,
        String relationship,
        String contactDetailsNote,
        int displayOrder
) {
}
