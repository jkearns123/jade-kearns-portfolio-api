package za.co.jadekearns.portfolio_api.additionalinformation.dto;

import za.co.jadekearns.portfolio_api.additionalinformation.domain.AdditionalInformationType;

import java.util.UUID;

public record AdditionalInformationResponse(
        UUID publicId,
        AdditionalInformationType informationType,
        String value,
        int displayOrder
) {
}
