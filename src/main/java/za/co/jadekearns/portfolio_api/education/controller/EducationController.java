package za.co.jadekearns.portfolio_api.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.education.dto.EducationResponse;
import za.co.jadekearns.portfolio_api.education.service.EducationService;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<EducationResponse> getEducation() {
        return educationService.getEducation();
    }
}
