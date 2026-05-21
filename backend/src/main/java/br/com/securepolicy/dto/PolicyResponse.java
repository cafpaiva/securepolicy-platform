package br.com.securepolicy.dto;

import br.com.securepolicy.domain.PolicyStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyResponse {

    private final Long id;
    private final String number;
    private final String customerName;
    private final String product;
    private final PolicyStatus status;
    private final BigDecimal premium;
    private final BigDecimal coverageAmount;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer riskScore;
    private final Integer claimsCount;

    public PolicyResponse(Long id, String number, String customerName, String product, PolicyStatus status,
                          BigDecimal premium, BigDecimal coverageAmount, LocalDate startDate, LocalDate endDate,
                          Integer riskScore, Integer claimsCount) {
        this.id = id;
        this.number = number;
        this.customerName = customerName;
        this.product = product;
        this.status = status;
        this.premium = premium;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.riskScore = riskScore;
        this.claimsCount = claimsCount;
    }

    public Long getId() { return id; }
    public String getNumber() { return number; }
    public String getCustomerName() { return customerName; }
    public String getProduct() { return product; }
    public PolicyStatus getStatus() { return status; }
    public BigDecimal getPremium() { return premium; }
    public BigDecimal getCoverageAmount() { return coverageAmount; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Integer getRiskScore() { return riskScore; }
    public Integer getClaimsCount() { return claimsCount; }
}
