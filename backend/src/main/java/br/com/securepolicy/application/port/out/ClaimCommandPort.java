package br.com.securepolicy.application.port.out;

import br.com.securepolicy.domain.InsuranceClaim;

public interface ClaimCommandPort {

    InsuranceClaim save(InsuranceClaim claim);
}
