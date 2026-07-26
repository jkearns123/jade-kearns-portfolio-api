package za.co.jadekearns.portfolio_api.interest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jadekearns.portfolio_api.interest.dto.InterestResponse;
import za.co.jadekearns.portfolio_api.interest.service.InterestService;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public List<InterestResponse> getInterests() {
        return interestService.getInterests();
    }
}
