package za.co.jadekearns.portfolio_api.profile.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.profile.dto.PortfolioProfileResponse;
import za.co.jadekearns.portfolio_api.profile.dto.ProfileImage;
import za.co.jadekearns.portfolio_api.profile.service.PortfolioProfileService;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
@RequestMapping("/api/profile")
public class PortfolioProfileController {
    private final PortfolioProfileService portfolioProfileService;

    public PortfolioProfileController(
            PortfolioProfileService portfolioProfileService
    ) {
        this.portfolioProfileService = portfolioProfileService;
    }

    @GetMapping
    public PortfolioProfileResponse getProfile() {
        return portfolioProfileService.getProfile();
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getProfileImage() {
        ProfileImage image = portfolioProfileService.getProfileImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.contentType()));
        headers.setContentLength(image.data().length);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename(
                                image.fileName(),
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
                .body(image.data());
    }
}
