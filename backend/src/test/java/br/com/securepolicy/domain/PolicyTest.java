package br.com.securepolicy.domain;

import br.com.securepolicy.domain.exception.ClaimCreationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PolicyTest {

    @Test
    void shouldOpenClaimAndLinkItToPolicy() {
        Policy policy = activePolicy();

        InsuranceClaim claim = policy.openClaim(
                "SIN-TEST001",
                "Colisao traseira em vistoria digital",
                new BigDecimal("12400.00"),
                LocalDate.now()
        );

        assertThat(claim.getProtocol()).isEqualTo("SIN-TEST001");
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.OPEN);
        assertThat(claim.getPolicy()).isEqualTo(policy);
        assertThat(policy.getClaims()).containsExactly(claim);
    }

    @Test
    void shouldAllowPendingRenewalPolicyToOpenClaimWithinCoverage() {
        Policy policy = policyWithStatus(PolicyStatus.PENDING_RENEWAL);

        InsuranceClaim claim = policy.openClaim(
                "SIN-TEST002",
                "Danos eletricos",
                new BigDecimal("3800.00"),
                LocalDate.now()
        );

        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.OPEN);
        assertThat(policy.getClaims()).hasSize(1);
    }

    @Test
    void shouldRejectCancelledPolicy() {
        Policy policy = policyWithStatus(PolicyStatus.CANCELLED);

        assertThatThrownBy(() -> policy.openClaim(
                "SIN-TEST003",
                "Perda parcial",
                new BigDecimal("3800.00"),
                LocalDate.now()
        )).isInstanceOf(ClaimCreationException.class)
                .hasMessage("Apolice nao permite abertura de sinistro no status CANCELLED");
    }

    @Test
    void shouldRejectClaimBeforePolicyStartDate() {
        Policy policy = activePolicy();

        assertThatThrownBy(() -> policy.openClaim(
                "SIN-TEST004",
                "Evento antes da vigencia",
                new BigDecimal("1000.00"),
                LocalDate.now().minusMonths(6)
        )).isInstanceOf(ClaimCreationException.class)
                .hasMessage("Apolice fora do periodo de vigencia");
    }

    @Test
    void shouldRejectNullAmount() {
        Policy policy = activePolicy();

        assertThatThrownBy(() -> policy.openClaim(
                "SIN-TEST005",
                "Valor ausente",
                null,
                LocalDate.now()
        )).isInstanceOf(ClaimCreationException.class)
                .hasMessage("Valor do sinistro deve ser positivo");
    }

    private Policy activePolicy() {
        return policyWithStatus(PolicyStatus.ACTIVE);
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
}
