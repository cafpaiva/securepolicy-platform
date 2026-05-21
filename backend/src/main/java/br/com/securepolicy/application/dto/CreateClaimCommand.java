package br.com.securepolicy.application.dto;

import java.math.BigDecimal;

public class CreateClaimCommand {

    private final Long policyId;
    private final String description;
    private final BigDecimal amount;

    public CreateClaimCommand(Long policyId, String description, BigDecimal amount) {
        this.policyId = policyId;
        this.description = description;
        this.amount = amount;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
