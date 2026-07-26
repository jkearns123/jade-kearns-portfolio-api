package za.co.jadekearns.portfolio_api.supportingdocument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocument;
import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocumentType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Long> {
    Optional<SupportingDocument> findByPublicId(UUID publicId);

    List<SupportingDocument>
    findAllByProfile_IdOrderByDisplayOrderAsc(
            Long profileId
    );

    List<SupportingDocument>
    findAllByProfile_PublicIdOrderByDisplayOrderAsc(
            UUID profilePublicId
    );

    List<SupportingDocument>
    findAllByProfile_IdAndDocumentTypeOrderByDisplayOrderAsc(
            Long profileId,
            SupportingDocumentType documentType
    );

    List<SupportingDocument>
    findAllByProfile_PublicIdAndDocumentTypeOrderByDisplayOrderAsc(
            UUID profilePublicId,
            SupportingDocumentType documentType
    );
}
