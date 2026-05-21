package br.com.securepolicy.application.mapper;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import org.springframework.stereotype.Component;

@Component
public class PolicyApplicationMapper {

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
