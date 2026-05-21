package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListPoliciesUseCase {

    private final PolicyQueryPort policyQueryPort;
    private final PolicyApplicationMapper mapper;

    public ListPoliciesUseCase(PolicyQueryPort policyQueryPort, PolicyApplicationMapper mapper) {
        this.policyQueryPort = policyQueryPort;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<PolicyResponse> execute() {
        return policyQueryPort.findAll().stream()
                .map(mapper::toPolicyResponse)
                .collect(Collectors.toList());
    }
}
