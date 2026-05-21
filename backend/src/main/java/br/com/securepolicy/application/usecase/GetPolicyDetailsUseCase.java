package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.domain.exception.PolicyNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetPolicyDetailsUseCase {

    private final PolicyQueryPort policyQueryPort;
    private final PolicyApplicationMapper mapper;

    public GetPolicyDetailsUseCase(PolicyQueryPort policyQueryPort, PolicyApplicationMapper mapper) {
        this.policyQueryPort = policyQueryPort;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public PolicyResponse execute(Long policyId) {
        Policy policy = policyQueryPort.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException(policyId));

        return mapper.toPolicyResponse(policy);
    }
}
