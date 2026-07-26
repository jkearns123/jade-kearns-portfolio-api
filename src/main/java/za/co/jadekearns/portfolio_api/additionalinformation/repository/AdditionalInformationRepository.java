package za.co.jadekearns.portfolio_api.additionalinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.additionalinformation.domain.AdditionalInformation;
import za.co.jadekearns.portfolio_api.additionalinformation.domain.AdditionalInformationType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdditionalInformationRepository extends JpaRepository<AdditionalInformation, Long> {
    Optional<AdditionalInformation> findByPublicId(UUID publicId);

    List<AdditionalInformation>
    findAllByProfile_IdOrderByDisplayOrderAsc(Long profileId);

    List<AdditionalInformation>
    findAllByProfile_PublicIdOrderByDisplayOrderAsc(
            UUID profilePublicId
    );

    List<AdditionalInformation>
    findAllByProfile_IdAndInformationTypeOrderByDisplayOrderAsc(
            Long profileId,
            AdditionalInformationType informationType
    );

    List<AdditionalInformation>
    findAllByProfile_PublicIdAndInformationTypeOrderByDisplayOrderAsc(
            UUID profilePublicId,
            AdditionalInformationType informationType
    );
}
