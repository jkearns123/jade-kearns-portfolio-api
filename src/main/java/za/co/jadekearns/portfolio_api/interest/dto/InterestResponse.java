package za.co.jadekearns.portfolio_api.interest.dto;

import java.util.UUID;

public record InterestResponse(
        UUID publicId,
        String name,
        String description,
        int displayOrder
) {
}
