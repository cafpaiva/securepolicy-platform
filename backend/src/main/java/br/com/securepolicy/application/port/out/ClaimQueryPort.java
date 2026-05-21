package br.com.securepolicy.application.port.out;

import br.com.securepolicy.domain.ClaimStatus;
import br.com.securepolicy.domain.InsuranceClaim;

import java.math.BigDecimal;
import java.util.List;

public interface ClaimQueryPort {

    List<InsuranceClaim> findAll();

    List<InsuranceClaim> findByStatus(ClaimStatus status);

    BigDecimal sumClaimAmount();
}
