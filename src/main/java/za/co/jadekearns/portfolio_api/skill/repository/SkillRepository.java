package za.co.jadekearns.portfolio_api.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.jadekearns.portfolio_api.skill.domain.Skill;
import za.co.jadekearns.portfolio_api.skill.domain.SkillType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByPublicId(UUID publicId);

    List<Skill> findAllByProfile_IdOrderByDisplayOrderAsc(
            Long profileId
    );

    List<Skill> findAllByProfile_PublicIdOrderByDisplayOrderAsc(
            UUID profilePublicId
    );

    List<Skill> findAllByProfile_PublicIdAndSkillTypeOrderByDisplayOrderAsc(
            UUID profilePublicId,
            SkillType skillType
    );

    List<Skill> findAllByProfile_PublicIdAndFeaturedTrueOrderByDisplayOrderAsc(
            UUID profilePublicId
    );
}
