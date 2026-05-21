package br.com.securepolicy.repository;

import br.com.securepolicy.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    @Query("select coalesce(sum(p.premium), 0) from Policy p")
    BigDecimal sumPremium();

    long countByRiskScoreGreaterThanEqual(Integer riskScore);
}
