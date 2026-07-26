package za.co.jadekearns.portfolio_api.additionalinformation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.additionalinformation.dto.AdditionalInformationResponse;
import za.co.jadekearns.portfolio_api.additionalinformation.service.AdditionalInformationService;

import java.util.List;

@RestController
@RequestMapping("/api/additional-information")
public class AdditionalInformationController {
    private final AdditionalInformationService additionalInformationService;

    public AdditionalInformationController(
            AdditionalInformationService additionalInformationService
    ) {
        this.additionalInformationService = additionalInformationService;
    }

    @GetMapping
    public List<AdditionalInformationResponse> getAdditionalInformation() {
        return additionalInformationService.getAdditionalInformation();
    }
}
