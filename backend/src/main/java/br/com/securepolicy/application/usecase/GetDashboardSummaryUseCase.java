package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.DashboardSummary;
import br.com.securepolicy.application.port.out.ClaimQueryPort;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.ClaimStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetDashboardSummaryUseCase {

    private final PolicyQueryPort policyQueryPort;
    private final ClaimQueryPort claimQueryPort;

    public GetDashboardSummaryUseCase(PolicyQueryPort policyQueryPort, ClaimQueryPort claimQueryPort) {
        this.policyQueryPort = policyQueryPort;
        this.claimQueryPort = claimQueryPort;
    }

    @Transactional(readOnly = true)
    public DashboardSummary execute() {
        return new DashboardSummary(
                policyQueryPort.count(),
                claimQueryPort.findByStatus(ClaimStatus.OPEN).size(),
                policyQueryPort.countByRiskScoreGreaterThanEqual(75),
                policyQueryPort.sumPremium(),
                claimQueryPort.sumClaimAmount()
        );
    }
}
