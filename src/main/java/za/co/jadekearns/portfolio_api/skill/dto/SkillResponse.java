package za.co.jadekearns.portfolio_api.skill.dto;

import za.co.jadekearns.portfolio_api.skill.domain.SkillType;

import java.util.UUID;

public record SkillResponse(
        UUID publicId,
        String name,
        SkillType skillType,
        String description,
        boolean featured,
        int displayOrder
) {
}
