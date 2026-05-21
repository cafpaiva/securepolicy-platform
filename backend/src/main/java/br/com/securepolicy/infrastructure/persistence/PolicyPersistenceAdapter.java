package br.com.securepolicy.infrastructure.persistence;

import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.Policy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class PolicyPersistenceAdapter implements PolicyQueryPort {

    private final JpaPolicyRepository policyRepository;

    public PolicyPersistenceAdapter(JpaPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<Policy> findAll() {
        return policyRepository.findAll();
    }

    @Override
    public Optional<Policy> findById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public long count() {
        return policyRepository.count();
    }

    @Override
    public long countByRiskScoreGreaterThanEqual(Integer riskScore) {
        return policyRepository.countByRiskScoreGreaterThanEqual(riskScore);
    }

    @Override
    public BigDecimal sumPremium() {
        return policyRepository.sumPremium();
    }
}
