package br.com.securepolicy.application.port.out;

import br.com.securepolicy.domain.Policy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PolicyQueryPort {

    List<Policy> findAll();

    Optional<Policy> findById(Long id);

    long count();

    long countByRiskScoreGreaterThanEqual(Integer riskScore);

    BigDecimal sumPremium();
}
