package br.com.securepolicy.service;

import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.dto.ClaimResponse;
import br.com.securepolicy.dto.PolicyResponse;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    public PolicyResponse toPolicyResponse(Policy policy) {
        return new PolicyResponse(
                policy.getId(),
                policy.getNumber(),
                policy.getCustomerName(),
                policy.getProduct(),
                policy.getStatus(),
                policy.getPremium(),
                policy.getCoverageAmount(),
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getRiskScore(),
                policy.getClaims().size()
        );
    }

    public ClaimResponse toClaimResponse(InsuranceClaim claim) {
        return new ClaimResponse(
                claim.getId(),
                claim.getProtocol(),
                claim.getDescription(),
                claim.getAmount(),
                claim.getStatus(),
                claim.getOpenedAt(),
                claim.getPolicy().getNumber(),
                claim.getPolicy().getCustomerName()
        );
    }
}
