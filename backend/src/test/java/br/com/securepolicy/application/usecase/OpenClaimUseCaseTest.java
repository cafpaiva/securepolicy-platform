package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.dto.CreateClaimCommand;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.ClaimCommandPort;
import br.com.securepolicy.application.port.out.ClaimProtocolGenerator;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.domain.PolicyStatus;
import br.com.securepolicy.domain.exception.PolicyNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OpenClaimUseCaseTest {

    private final PolicyApplicationMapper mapper = new PolicyApplicationMapper();

    @Test
    void shouldOpenClaimForExistingPolicy() {
        Policy policy = new Policy(
                "AUTO-2026-0001",
                "Mariana Azevedo",
                "Seguro Auto Premium",
                PolicyStatus.ACTIVE,
                new BigDecimal("489.90"),
                new BigDecimal("98000.00"),
                LocalDate.now().minusMonths(5),
                LocalDate.now().plusMonths(7),
                42
        );
        PolicyQueryPort policyPort = new StubPolicyQueryPort(Optional.of(policy));
        ClaimCommandPort claimPort = claim -> claim;
        ClaimProtocolGenerator protocolGenerator = () -> "SIN-TEST001";
        OpenClaimUseCase useCase = new OpenClaimUseCase(policyPort, claimPort, protocolGenerator, mapper);

        ClaimResponse response = useCase.execute(new CreateClaimCommand(
                1L,
                "Colisao traseira em vistoria digital",
                new BigDecimal("12400.00")
        ));

        assertThat(response.getProtocol()).isEqualTo("SIN-TEST001");
        assertThat(response.getPolicyNumber()).isEqualTo("AUTO-2026-0001");
        assertThat(response.getCustomerName()).isEqualTo("Mariana Azevedo");
        assertThat(response.getAmount()).isEqualByComparingTo("12400.00");
    }

    @Test
    void shouldFailWhenPolicyDoesNotExist() {
        OpenClaimUseCase useCase = new OpenClaimUseCase(
                new StubPolicyQueryPort(Optional.empty()),
                claim -> claim,
                () -> "SIN-TEST001",
                mapper
        );

        assertThatThrownBy(() -> useCase.execute(new CreateClaimCommand(
                999L,
                "Dano eletrico",
                new BigDecimal("3800.00")
        ))).isInstanceOf(PolicyNotFoundException.class)
                .hasMessage("Apolice nao encontrada: 999");
    }

    private static class StubPolicyQueryPort implements PolicyQueryPort {

        private final Optional<Policy> policy;

        StubPolicyQueryPort(Optional<Policy> policy) {
            this.policy = policy;
        }

        @Override
        public List<Policy> findAll() {
            return policy.map(Collections::singletonList).orElseGet(Collections::emptyList);
        }

        @Override
        public Optional<Policy> findById(Long id) {
            return policy;
        }

        @Override
        public long count() {
            return policy.isPresent() ? 1 : 0;
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
