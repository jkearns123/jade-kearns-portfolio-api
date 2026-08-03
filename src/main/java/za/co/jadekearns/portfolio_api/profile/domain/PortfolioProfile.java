package za.co.jadekearns.portfolio_api.profile.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import za.co.jadekearns.portfolio_api.sharedkernel.domain.AbstractAuditingEntity;

import java.util.UUID;

@Entity
@Table(name = "portfolio_profile")
public class PortfolioProfile extends AbstractAuditingEntity<Long> {
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

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "professional_title", nullable = false, length = 255)
    private String professionalTitle;

    @Column(name = "headline", nullable = false, length = 255)
    private String headline;

    @Column(name = "professional_summary", nullable = false, columnDefinition = "TEXT")
    private String professionalSummary;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "mobile_number", length = 30)
    private String mobileNumber;

    @Column(name = "show_email", nullable = false)
    private boolean showEmail = true;

    @Column(name = "show_mobile_number", nullable = false)
    private boolean showMobileNumber = false;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "profile_image_file_name", length = 255)
    private String profileImageFileName;

    @Column(name = "profile_image_content_type", length = 100)
    private String profileImageContentType;

    @Column(name = "linkedin_url", length = 1000)
    private String linkedInUrl;

    @Column(name = "github_url", length = 1000)
    private String githubUrl;

    @JdbcTypeCode(SqlTypes.LONG32VARBINARY)
    @Column(name = "profile_image_data", columnDefinition = "BYTEA")
    private byte[] profileImageData;

    @Column(name = "resume_url", length = 1000)
    private String resumeUrl;

    @Column(name = "available_for_work", nullable = false)
    private boolean availableForWork = true;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isShowEmail() {
        return showEmail;
    }

    public void setShowEmail(boolean showEmail) {
        this.showEmail = showEmail;
    }

    public boolean isShowMobileNumber() {
        return showMobileNumber;
    }

    public void setShowMobileNumber(boolean showMobileNumber) {
        this.showMobileNumber = showMobileNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public boolean isAvailableForWork() {
        return availableForWork;
    }

    public void setAvailableForWork(boolean availableForWork) {
        this.availableForWork = availableForWork;
    }

    public String getProfileImageFileName() {
        return profileImageFileName;
    }

    public void setProfileImageFileName(String profileImageFileName) {
        this.profileImageFileName = profileImageFileName;
    }

    public String getProfileImageContentType() {
        return profileImageContentType;
    }

    public void setProfileImageContentType(String profileImageContentType) {
        this.profileImageContentType = profileImageContentType;
    }

    public byte[] getProfileImageData() {
        return profileImageData;
    }

    public void setProfileImageData(byte[] profileImageData) {
        this.profileImageData = profileImageData;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
