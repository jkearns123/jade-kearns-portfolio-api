package za.co.jadekearns.portfolio_api.profile.dto;

public record ProfileImage(
        String fileName,
        String contentType,
        byte[] data
) {
}
