package br.com.securepolicy.controller;

import br.com.securepolicy.dto.ClaimResponse;
import br.com.securepolicy.dto.CreateClaimRequest;
import br.com.securepolicy.service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class ClaimController {

    private final PolicyService policyService;

    public ClaimController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<ClaimResponse> listClaims() {
        return policyService.listClaims();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse createClaim(@Valid @RequestBody CreateClaimRequest request) {
        return policyService.createClaim(request);
    }
}
