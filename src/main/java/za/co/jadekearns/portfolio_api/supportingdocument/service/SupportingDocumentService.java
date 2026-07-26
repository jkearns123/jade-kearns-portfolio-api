package za.co.jadekearns.portfolio_api.supportingdocument.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;
import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocument;
import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocumentType;
import za.co.jadekearns.portfolio_api.supportingdocument.dto.SupportingDocumentFile;
import za.co.jadekearns.portfolio_api.supportingdocument.dto.SupportingDocumentResponse;
import za.co.jadekearns.portfolio_api.supportingdocument.repository.SupportingDocumentRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SupportingDocumentService {
    private static final String FILE_ENDPOINT_PREFIX =
            "/api/supporting-documents/";

    private final SupportingDocumentRepository supportingDocumentRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public SupportingDocumentService(
            SupportingDocumentRepository supportingDocumentRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.supportingDocumentRepository = supportingDocumentRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<SupportingDocumentResponse> getSupportingDocuments(
            SupportingDocumentType documentType
    ) {
        PortfolioProfile profile = getPortfolioProfile();

        List<SupportingDocument> documents = documentType == null
                ? supportingDocumentRepository
                  .findAllByProfile_IdOrderByDisplayOrderAsc(
                          profile.getId()
                  )
                : supportingDocumentRepository
                  .findAllByProfile_IdAndDocumentTypeOrderByDisplayOrderAsc(
                          profile.getId(),
                          documentType
                  );

        return documents.stream()
                .map(this::toResponse)
                .toList();
    }

    public SupportingDocumentFile getSupportingDocumentFile(
            UUID publicId
    ) {
        SupportingDocument document = supportingDocumentRepository
                .findByPublicId(publicId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Supporting document not found"
                ));

        byte[] documentData = document.getDocumentData();

        if (documentData == null || documentData.length == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Supporting document file not found"
            );
        }

        return new SupportingDocumentFile(
                document.getFileName(),
                document.getContentType(),
                documentData
        );
    }

    private PortfolioProfile getPortfolioProfile() {
        return portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));
    }

    private SupportingDocumentResponse toResponse(
            SupportingDocument document
    ) {
        return new SupportingDocumentResponse(
                document.getPublicId(),
                document.getDocumentType(),
                document.getTitle(),
                document.getSummary(),
                document.getIssuerName(),
                document.getIssuerTitle(),
                document.getIssuerOrganization(),
                document.getFileName(),
                document.getContentType(),
                FILE_ENDPOINT_PREFIX
                        + document.getPublicId()
                        + "/file",
                document.getDisplayOrder()
        );
    }
}
