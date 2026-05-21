package br.com.securepolicy.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_claims")
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String protocol;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ClaimStatus status;

    @Column(nullable = false)
    private LocalDate openedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    protected InsuranceClaim() {
    }

    public InsuranceClaim(String protocol, String description, BigDecimal amount, ClaimStatus status, LocalDate openedAt) {
        this.protocol = protocol;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.openedAt = openedAt;
    }

    public Long getId() {
        return id;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public Policy getPolicy() {
        return policy;
    }

    void assignPolicy(Policy policy) {
        this.policy = policy;
    }
}
