package za.co.jadekearns.portfolio_api.experience.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.experience.dto.ExperienceResponse;
import za.co.jadekearns.portfolio_api.experience.dto.ExperienceYearsResponse;
import za.co.jadekearns.portfolio_api.experience.service.ExperienceService;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<ExperienceResponse> getExperience() {
        return experienceService.getExperience();
    }

    @GetMapping("/years")
    public ExperienceYearsResponse getYearsOfExperience() {
        return new ExperienceYearsResponse(
                experienceService.getYearsOfExperience()
        );
    }

    @GetMapping("/systemanalystyears")
    public ExperienceYearsResponse getSystemAnalystYearsOfExperience() {
        return new ExperienceYearsResponse(
                experienceService.getSystemAnalystYearsOfExperience()
        );
    }
}
