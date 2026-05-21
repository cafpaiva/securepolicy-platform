package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ListPoliciesQuery;
import br.com.securepolicy.application.dto.PolicyResponse;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.domain.PolicyStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ListPoliciesUseCaseTest {

    @Test
    void shouldListPoliciesFromOutputPort() {
        PolicyQueryPort policyPort = new StubPolicyQueryPort(List.of(policy(
                "AUTO-2026-0001",
                "Mariana Azevedo",
                "Seguro Auto Premium",
                PolicyStatus.ACTIVE,
                42
        )));
        ListPoliciesUseCase useCase = new ListPoliciesUseCase(policyPort, new PolicyApplicationMapper());

        List<PolicyResponse> policies = useCase.execute();

        assertThat(policies).hasSize(1);
        assertThat(policies.get(0).getNumber()).isEqualTo("AUTO-2026-0001");
        assertThat(policies.get(0).getCustomerName()).isEqualTo("Mariana Azevedo");
    }

    @Test
    void shouldOrderPoliciesByRiskScoreDescending() {
        ListPoliciesUseCase useCase = new ListPoliciesUseCase(new StubPolicyQueryPort(List.of(
                policy("VIDA-2026-0108", "Ana Paula Rocha", "Vida Individual", PolicyStatus.ACTIVE, 24),
                policy("RES-2026-0042", "Carlos Mendes", "Residencial Completo", PolicyStatus.PENDING_RENEWAL, 78),
                policy("AUTO-2026-0001", "Mariana Azevedo", "Seguro Auto Premium", PolicyStatus.ACTIVE, 42)
        )), new PolicyApplicationMapper());

        List<PolicyResponse> policies = useCase.execute();

        assertThat(policies)
                .extracting(PolicyResponse::getNumber)
                .containsExactly("RES-2026-0042", "AUTO-2026-0001", "VIDA-2026-0108");
    }

    @Test
    void shouldFilterPoliciesByStatus() {
        ListPoliciesUseCase useCase = new ListPoliciesUseCase(new StubPolicyQueryPort(List.of(
                policy("VIDA-2026-0108", "Ana Paula Rocha", "Vida Individual", PolicyStatus.ACTIVE, 24),
                policy("RES-2026-0042", "Carlos Mendes", "Residencial Completo", PolicyStatus.PENDING_RENEWAL, 78),
                policy("AUTO-2026-0001", "Mariana Azevedo", "Seguro Auto Premium", PolicyStatus.ACTIVE, 42)
        )), new PolicyApplicationMapper());

        List<PolicyResponse> policies = useCase.execute(new ListPoliciesQuery(PolicyStatus.ACTIVE, null));

        assertThat(policies)
                .extracting(PolicyResponse::getNumber)
                .containsExactly("AUTO-2026-0001", "VIDA-2026-0108");
    }

    @Test
    void shouldFilterPoliciesBySearchTerm() {
        ListPoliciesUseCase useCase = new ListPoliciesUseCase(new StubPolicyQueryPort(List.of(
                policy("VIDA-2026-0108", "Ana Paula Rocha", "Vida Individual", PolicyStatus.ACTIVE, 24),
                policy("RES-2026-0042", "Carlos Mendes", "Residencial Completo", PolicyStatus.PENDING_RENEWAL, 78),
                policy("AUTO-2026-0001", "Mariana Azevedo", "Seguro Auto Premium", PolicyStatus.ACTIVE, 42)
        )), new PolicyApplicationMapper());

        List<PolicyResponse> policies = useCase.execute(new ListPoliciesQuery(null, "mariana"));

        assertThat(policies)
                .extracting(PolicyResponse::getNumber)
                .containsExactly("AUTO-2026-0001");
    }

    private Policy policy(String number, String customerName, String product, PolicyStatus status, Integer riskScore) {
        return new Policy(
                number,
                customerName,
                product,
                status,
                new BigDecimal("489.90"),
                new BigDecimal("98000.00"),
                LocalDate.now().minusMonths(5),
                LocalDate.now().plusMonths(7),
                riskScore
        );
    }

    private static class StubPolicyQueryPort implements PolicyQueryPort {

        private final List<Policy> policies;

        StubPolicyQueryPort(List<Policy> policies) {
            this.policies = policies;
        }

        @Override
        public List<Policy> findAll() {
            return policies;
        }

        @Override
        public Optional<Policy> findById(Long id) {
            return policies.stream().findFirst();
        }

        @Override
        public long count() {
            return policies.size();
        }

        @Override
        public long countByRiskScoreGreaterThanEqual(Integer riskScore) {
            return 0;
        }

        @Override
        public BigDecimal sumPremium() {
            return BigDecimal.ZERO;
        }
    }
}
