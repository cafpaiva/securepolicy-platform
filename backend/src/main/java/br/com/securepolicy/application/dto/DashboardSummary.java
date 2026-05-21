package br.com.securepolicy.application.dto;

import java.math.BigDecimal;

public class DashboardSummary {

    private final long activePolicies;
    private final long openClaims;
    private final long highRiskPolicies;
    private final BigDecimal monthlyPremium;
    private final BigDecimal claimedAmount;

    public DashboardSummary(long activePolicies, long openClaims, long highRiskPolicies, BigDecimal monthlyPremium,
                            BigDecimal claimedAmount) {
        this.activePolicies = activePolicies;
        this.openClaims = openClaims;
        this.highRiskPolicies = highRiskPolicies;
        this.monthlyPremium = monthlyPremium;
        this.claimedAmount = claimedAmount;
    }

    public long getActivePolicies() { return activePolicies; }
    public long getOpenClaims() { return openClaims; }
    public long getHighRiskPolicies() { return highRiskPolicies; }
    public BigDecimal getMonthlyPremium() { return monthlyPremium; }
    public BigDecimal getClaimedAmount() { return claimedAmount; }
}
