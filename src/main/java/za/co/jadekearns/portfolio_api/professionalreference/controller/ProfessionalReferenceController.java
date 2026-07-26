package za.co.jadekearns.portfolio_api.professionalreference.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.professionalreference.dto.ProfessionalReferenceResponse;
import za.co.jadekearns.portfolio_api.professionalreference.service.ProfessionalReferenceService;

import java.util.List;

@RestController
@RequestMapping("/api/professional-references")
public class ProfessionalReferenceController {
    private final ProfessionalReferenceService professionalReferenceService;

    public ProfessionalReferenceController(
            ProfessionalReferenceService professionalReferenceService
    ) {
        this.professionalReferenceService = professionalReferenceService;
    }

    @GetMapping
    public List<ProfessionalReferenceResponse> getProfessionalReferences() {
        return professionalReferenceService.getProfessionalReferences();
    }
}
