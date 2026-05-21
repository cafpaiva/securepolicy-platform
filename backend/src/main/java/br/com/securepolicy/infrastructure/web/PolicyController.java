package br.com.securepolicy.infrastructure.web;

import br.com.securepolicy.application.dto.ListPoliciesQuery;
import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.application.usecase.GetPolicyDetailsUseCase;
import br.com.securepolicy.application.usecase.ListPoliciesUseCase;
import br.com.securepolicy.domain.PolicyStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class PolicyController {

    private final ListPoliciesUseCase listPoliciesUseCase;
    private final GetPolicyDetailsUseCase getPolicyDetailsUseCase;

    public PolicyController(ListPoliciesUseCase listPoliciesUseCase, GetPolicyDetailsUseCase getPolicyDetailsUseCase) {
        this.listPoliciesUseCase = listPoliciesUseCase;
        this.getPolicyDetailsUseCase = getPolicyDetailsUseCase;
    }

    @GetMapping
    public List<PolicyResponse> listPolicies(@RequestParam(required = false) PolicyStatus status,
                                             @RequestParam(required = false) String search) {
        return listPoliciesUseCase.execute(new ListPoliciesQuery(status, search));
    }

    @GetMapping("/{id}")
    public PolicyResponse getPolicy(@PathVariable Long id) {
        return getPolicyDetailsUseCase.execute(id);
    }
}
