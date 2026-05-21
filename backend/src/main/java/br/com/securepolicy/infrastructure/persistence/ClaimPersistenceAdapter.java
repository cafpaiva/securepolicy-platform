package br.com.securepolicy.infrastructure.persistence;

import br.com.securepolicy.application.port.out.ClaimCommandPort;
import br.com.securepolicy.application.port.out.ClaimQueryPort;
import br.com.securepolicy.domain.ClaimStatus;
import br.com.securepolicy.domain.InsuranceClaim;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ClaimPersistenceAdapter implements ClaimQueryPort, ClaimCommandPort {

    private final JpaClaimRepository claimRepository;

    public ClaimPersistenceAdapter(JpaClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public List<InsuranceClaim> findAll() {
        return claimRepository.findAll();
    }

    @Override
    public List<InsuranceClaim> findByStatus(ClaimStatus status) {
        return claimRepository.findByStatus(status);
    }

    @Override
    public BigDecimal sumClaimAmount() {
        return claimRepository.sumClaimAmount();
    }

    @Override
    public InsuranceClaim save(InsuranceClaim claim) {
        return claimRepository.save(claim);
    }
}
