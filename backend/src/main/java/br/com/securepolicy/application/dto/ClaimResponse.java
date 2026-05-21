package br.com.securepolicy.application.dto;

import br.com.securepolicy.domain.ClaimStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimResponse {

    private final Long id;
    private final String protocol;
    private final String description;
    private final BigDecimal amount;
    private final ClaimStatus status;
    private final LocalDate openedAt;
    private final String policyNumber;
    private final String customerName;

    public ClaimResponse(Long id, String protocol, String description, BigDecimal amount, ClaimStatus status,
                         LocalDate openedAt, String policyNumber, String customerName) {
        this.id = id;
        this.protocol = protocol;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.openedAt = openedAt;
        this.policyNumber = policyNumber;
        this.customerName = customerName;
    }

    public Long getId() { return id; }
    public String getProtocol() { return protocol; }
    public String getDescription() { return description; }
    public BigDecimal getAmount() { return amount; }
    public ClaimStatus getStatus() { return status; }
    public LocalDate getOpenedAt() { return openedAt; }
    public String getPolicyNumber() { return policyNumber; }
    public String getCustomerName() { return customerName; }
}
