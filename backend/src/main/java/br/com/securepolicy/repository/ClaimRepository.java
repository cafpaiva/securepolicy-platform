package br.com.securepolicy.repository;

import br.com.securepolicy.domain.ClaimStatus;
import br.com.securepolicy.domain.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ClaimRepository extends JpaRepository<InsuranceClaim, Long> {

    List<InsuranceClaim> findByStatus(ClaimStatus status);

    @Query("select coalesce(sum(c.amount), 0) from InsuranceClaim c")
    BigDecimal sumClaimAmount();
}
