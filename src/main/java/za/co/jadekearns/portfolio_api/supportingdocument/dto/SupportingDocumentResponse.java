package za.co.jadekearns.portfolio_api.supportingdocument.dto;

import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocumentType;

import java.util.UUID;

public record SupportingDocumentResponse(
        UUID publicId,
        SupportingDocumentType documentType,
        String title,
        String summary,
        String issuerName,
        String issuerTitle,
        String issuerOrganization,
        String fileName,
        String contentType,
        String fileUrl,
        int displayOrder
) {
}
