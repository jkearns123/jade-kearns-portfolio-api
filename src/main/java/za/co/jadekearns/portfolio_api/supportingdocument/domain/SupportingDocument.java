package za.co.jadekearns.portfolio_api.supportingdocument.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.sharedkernel.domain.AbstractAuditingEntity;

import java.util.UUID;

@Entity
@Table(name = "supporting_document")
public class SupportingDocument extends AbstractAuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "public_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private PortfolioProfile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 50)
    private SupportingDocumentType documentType;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "issuer_name", length = 255)
    private String issuerName;

    @Column(name = "issuer_title", length = 255)
    private String issuerTitle;

    @Column(name = "issuer_organization", length = 255)
    private String issuerOrganization;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType;

    /*
     * LONG32VARBINARY keeps the Java byte[] mapped to PostgreSQL BYTEA.
     * Do not replace this with @Lob, because PostgreSQL may then use its
     * large-object/OID handling instead of the BYTEA column in Liquibase.
     */
    @JdbcTypeCode(SqlTypes.LONG32VARBINARY)
    @Column(name = "document_data", nullable = false, columnDefinition = "BYTEA")
    private byte[] documentData;

    @Column(name = "display_order", nullable = false)
    private int displayOrder = 0;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public PortfolioProfile getProfile() {
        return profile;
    }

    public void setProfile(PortfolioProfile profile) {
        this.profile = profile;
    }

    public SupportingDocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(SupportingDocumentType documentType) {
        this.documentType = documentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getIssuerTitle() {
        return issuerTitle;
    }

    public void setIssuerTitle(String issuerTitle) {
        this.issuerTitle = issuerTitle;
    }

    public String getIssuerOrganization() {
        return issuerOrganization;
    }

    public void setIssuerOrganization(String issuerOrganization) {
        this.issuerOrganization = issuerOrganization;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getDocumentData() {
        return documentData;
    }

    public void setDocumentData(byte[] documentData) {
        this.documentData = documentData;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
