package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.dto.CreateClaimCommand;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.ClaimCommandPort;
import br.com.securepolicy.application.port.out.ClaimProtocolGenerator;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.domain.exception.PolicyNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OpenClaimUseCase {

    private final PolicyQueryPort policyQueryPort;
    private final ClaimCommandPort claimCommandPort;
    private final ClaimProtocolGenerator protocolGenerator;
    private final PolicyApplicationMapper mapper;

    public OpenClaimUseCase(PolicyQueryPort policyQueryPort, ClaimCommandPort claimCommandPort,
                            ClaimProtocolGenerator protocolGenerator, PolicyApplicationMapper mapper) {
        this.policyQueryPort = policyQueryPort;
        this.claimCommandPort = claimCommandPort;
        this.protocolGenerator = protocolGenerator;
        this.mapper = mapper;
    }

    @Transactional
    public ClaimResponse execute(CreateClaimCommand command) {
        Policy policy = policyQueryPort.findById(command.getPolicyId())
                .orElseThrow(() -> new PolicyNotFoundException(command.getPolicyId()));

        InsuranceClaim claim = policy.openClaim(
                protocolGenerator.nextProtocol(),
                command.getDescription(),
                command.getAmount(),
                LocalDate.now()
        );

        return mapper.toClaimResponse(claimCommandPort.save(claim));
    }
}
