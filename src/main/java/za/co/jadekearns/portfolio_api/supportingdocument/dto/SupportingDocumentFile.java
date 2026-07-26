package za.co.jadekearns.portfolio_api.supportingdocument.dto;

public record SupportingDocumentFile(
        String fileName,
        String contentType,
        byte[] data
) {
}
