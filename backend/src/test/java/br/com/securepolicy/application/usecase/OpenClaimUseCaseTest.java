package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.dto.CreateClaimCommand;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.ClaimCommandPort;
import br.com.securepolicy.application.port.out.ClaimProtocolGenerator;
import br.com.securepolicy.application.port.out.PolicyQueryPort;
import br.com.securepolicy.domain.ClaimStatus;
import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.domain.PolicyStatus;
import br.com.securepolicy.domain.exception.ClaimCreationException;
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
        assertThat(response.getStatus()).isEqualTo(ClaimStatus.OPEN);
        assertThat(policy.getClaims()).hasSize(1);
        assertThat(policy.getClaims().get(0).getPolicy()).isEqualTo(policy);
    }

    @Test
    void shouldPersistOpenedClaim() {
        CapturingClaimCommandPort claimPort = new CapturingClaimCommandPort();
        Policy policy = policyWithStatus(PolicyStatus.ACTIVE);
        OpenClaimUseCase useCase = new OpenClaimUseCase(
                new StubPolicyQueryPort(Optional.of(policy)),
                claimPort,
                () -> "SIN-TEST001",
                mapper
        );

        useCase.execute(validCommand());

        assertThat(claimPort.savedClaim).isNotNull();
        assertThat(claimPort.savedClaim.getProtocol()).isEqualTo("SIN-TEST001");
        assertThat(claimPort.savedClaim.getPolicy()).isEqualTo(policy);
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

    @Test
    void shouldFailWhenPolicyStatusDoesNotAllowClaims() {
        OpenClaimUseCase useCase = newUseCaseFor(policyWithStatus(PolicyStatus.SUSPENDED));

        assertThatThrownBy(() -> useCase.execute(validCommand()))
                .isInstanceOf(ClaimCreationException.class)
                .hasMessage("Apolice nao permite abertura de sinistro no status SUSPENDED");
    }

    @Test
    void shouldFailWhenPolicyIsExpired() {
        Policy expiredPolicy = new Policy(
                "AUTO-2025-0001",
                "Mariana Azevedo",
                "Seguro Auto Premium",
                PolicyStatus.ACTIVE,
                new BigDecimal("489.90"),
                new BigDecimal("98000.00"),
                LocalDate.now().minusMonths(12),
                LocalDate.now().minusDays(1),
                42
        );
        OpenClaimUseCase useCase = newUseCaseFor(expiredPolicy);

        assertThatThrownBy(() -> useCase.execute(validCommand()))
                .isInstanceOf(ClaimCreationException.class)
                .hasMessage("Apolice fora do periodo de vigencia");
    }

    @Test
    void shouldFailWhenClaimAmountIsNotPositive() {
        OpenClaimUseCase useCase = newUseCaseFor(policyWithStatus(PolicyStatus.ACTIVE));

        assertThatThrownBy(() -> useCase.execute(new CreateClaimCommand(
                1L,
                "Valor invalido",
                BigDecimal.ZERO
        ))).isInstanceOf(ClaimCreationException.class)
                .hasMessage("Valor do sinistro deve ser positivo");
    }

    @Test
    void shouldFailWhenClaimAmountExceedsCoverage() {
        OpenClaimUseCase useCase = newUseCaseFor(policyWithStatus(PolicyStatus.ACTIVE));

        assertThatThrownBy(() -> useCase.execute(new CreateClaimCommand(
                1L,
                "Perda integral acima da cobertura",
                new BigDecimal("120000.00")
        ))).isInstanceOf(ClaimCreationException.class)
                .hasMessage("Valor do sinistro nao pode exceder a cobertura contratada");
    }

    private OpenClaimUseCase newUseCaseFor(Policy policy) {
        return new OpenClaimUseCase(
                new StubPolicyQueryPort(Optional.of(policy)),
                claim -> claim,
                () -> "SIN-TEST001",
                mapper
        );
    }

    private CreateClaimCommand validCommand() {
        return new CreateClaimCommand(
                1L,
                "Colisao traseira em vistoria digital",
                new BigDecimal("12400.00")
        );
    }

    private Policy policyWithStatus(PolicyStatus status) {
        return new Policy(
                "AUTO-2026-0001",
                "Mariana Azevedo",
                "Seguro Auto Premium",
                status,
                new BigDecimal("489.90"),
                new BigDecimal("98000.00"),
                LocalDate.now().minusMonths(5),
                LocalDate.now().plusMonths(7),
                42
        );
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

    private static class CapturingClaimCommandPort implements ClaimCommandPort {

        private InsuranceClaim savedClaim;

        @Override
        public InsuranceClaim save(InsuranceClaim claim) {
            this.savedClaim = claim;
            return claim;
        }
    }
}
