package za.co.jadekearns.portfolio_api.additionalinformation.domain;

import jakarta.persistence.*;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.sharedkernel.domain.AbstractAuditingEntity;

import java.util.UUID;

@Entity
@Table(name = "additional_information")
public class AdditionalInformation extends AbstractAuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "public_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "profile_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_additional_information_profile"
            )
    )
    private PortfolioProfile profile;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "information_type",
            nullable = false,
            length = 50
    )
    private AdditionalInformationType informationType;

    @Column(
            name = "value",
            nullable = false,
            length = 255
    )
    private String value;

    @Column(
            name = "display_order",
            nullable = false
    )
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

    public AdditionalInformationType getInformationType() {
        return informationType;
    }

    public void setInformationType(AdditionalInformationType informationType) {
        this.informationType = informationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
