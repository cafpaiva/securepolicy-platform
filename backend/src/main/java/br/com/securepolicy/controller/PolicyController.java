package br.com.securepolicy.controller;

import br.com.securepolicy.dto.PolicyResponse;
import br.com.securepolicy.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<PolicyResponse> listPolicies() {
        return policyService.listPolicies();
    }

    @GetMapping("/{id}")
    public PolicyResponse getPolicy(@PathVariable Long id) {
        return policyService.getPolicy(id);
    }
}
