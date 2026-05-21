package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ListPoliciesQuery;
import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.Policy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return execute(ListPoliciesQuery.empty());
    }

    @Transactional(readOnly = true)
    public List<PolicyResponse> execute(ListPoliciesQuery query) {
        return policyQueryPort.findAll().stream()
                .filter(policy -> matchesStatus(policy, query))
                .filter(policy -> matchesSearch(policy, query))
                .sorted(Comparator.comparing(Policy::getRiskScore).reversed()
                        .thenComparing(Policy::getCustomerName))
                .map(mapper::toPolicyResponse)
                .collect(Collectors.toList());
    }

    private boolean matchesStatus(Policy policy, ListPoliciesQuery query) {
        return query.getStatus()
                .map(status -> policy.getStatus() == status)
                .orElse(true);
    }

    private boolean matchesSearch(Policy policy, ListPoliciesQuery query) {
        return query.getSearch()
                .map(search -> Stream.of(policy.getNumber(), policy.getCustomerName(), policy.getProduct())
                        .map(String::toLowerCase)
                        .anyMatch(value -> value.contains(search)))
                .orElse(true);
    }
}
