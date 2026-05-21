package br.com.securepolicy.infrastructure.persistence;

import br.com.securepolicy.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface JpaPolicyRepository extends JpaRepository<Policy, Long> {

    @Query("select coalesce(sum(p.premium), 0) from Policy p")
    BigDecimal sumPremium();

    long countByRiskScoreGreaterThanEqual(Integer riskScore);
}
