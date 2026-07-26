package za.co.jadekearns.portfolio_api.supportingdocument.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import za.co.jadekearns.portfolio_api.supportingdocument.domain.SupportingDocumentType;
import za.co.jadekearns.portfolio_api.supportingdocument.dto.SupportingDocumentFile;
import za.co.jadekearns.portfolio_api.supportingdocument.dto.SupportingDocumentResponse;
import za.co.jadekearns.portfolio_api.supportingdocument.service.SupportingDocumentService;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supporting-documents")
public class SupportingDocumentController {
    private final SupportingDocumentService supportingDocumentService;

    public SupportingDocumentController(
            SupportingDocumentService supportingDocumentService
    ) {
        this.supportingDocumentService = supportingDocumentService;
    }

    @GetMapping
    public List<SupportingDocumentResponse> getSupportingDocuments(
            @RequestParam(required = false)
            SupportingDocumentType documentType
    ) {
        return supportingDocumentService.getSupportingDocuments(documentType);
    }

    @GetMapping("/{publicId}/file")
    public ResponseEntity<byte[]> getSupportingDocumentFile(
            @PathVariable UUID publicId
    ) {
        SupportingDocumentFile document =
                supportingDocumentService.getSupportingDocumentFile(publicId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType(document.contentType())
        );
        headers.setContentLength(document.data().length);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename(
                                document.fileName(),
                                StandardCharsets.UTF_8
                        )
                        .build()
        );
        headers.setCacheControl(
                CacheControl.maxAge(Duration.ofHours(1))
                        .cachePublic()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(document.data());
    }
}
