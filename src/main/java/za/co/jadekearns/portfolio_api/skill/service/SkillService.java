package za.co.jadekearns.portfolio_api.skill.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import za.co.jadekearns.portfolio_api.profile.domain.PortfolioProfile;
import za.co.jadekearns.portfolio_api.profile.repository.PortfolioProfileRepository;
import za.co.jadekearns.portfolio_api.skill.domain.Skill;
import za.co.jadekearns.portfolio_api.skill.dto.SkillResponse;
import za.co.jadekearns.portfolio_api.skill.repository.SkillRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SkillService {
    private final SkillRepository skillRepository;
    private final PortfolioProfileRepository portfolioProfileRepository;

    public SkillService(
            SkillRepository skillRepository,
            PortfolioProfileRepository portfolioProfileRepository
    ) {
        this.skillRepository = skillRepository;
        this.portfolioProfileRepository = portfolioProfileRepository;
    }

    public List<SkillResponse> getSkills() {
        PortfolioProfile profile = portfolioProfileRepository
                .findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Portfolio profile not found"
                ));

        return skillRepository
                .findAllByProfile_IdOrderByDisplayOrderAsc(profile.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private SkillResponse toResponse(Skill skill) {
        return new SkillResponse(
                skill.getPublicId(),
                skill.getName(),
                skill.getSkillType(),
                skill.getDescription(),
                skill.isFeatured(),
                skill.getDisplayOrder()
        );
    }
}
