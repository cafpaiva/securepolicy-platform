package br.com.securepolicy.controller;

import br.com.securepolicy.dto.DashboardSummary;
import br.com.securepolicy.service.PolicyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class DashboardController {

    private final PolicyService policyService;

    public DashboardController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/summary")
    public DashboardSummary summary() {
        return policyService.dashboardSummary();
    }
}
