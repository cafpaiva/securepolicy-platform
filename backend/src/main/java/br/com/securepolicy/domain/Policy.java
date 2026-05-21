package br.com.securepolicy.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String number;

    @Column(nullable = false, length = 120)
    private String customerName;

    @Column(nullable = false, length = 80)
    private String product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PolicyStatus status;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal premium;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal coverageAmount;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer riskScore;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsuranceClaim> claims = new ArrayList<>();

    protected Policy() {
    }

    public Policy(String number, String customerName, String product, PolicyStatus status, BigDecimal premium,
                  BigDecimal coverageAmount, LocalDate startDate, LocalDate endDate, Integer riskScore) {
        this.number = number;
        this.customerName = customerName;
        this.product = product;
        this.status = status;
        this.premium = premium;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.riskScore = riskScore;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProduct() {
        return product;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public List<InsuranceClaim> getClaims() {
        return claims;
    }

    public void addClaim(InsuranceClaim claim) {
        claims.add(claim);
        claim.assignPolicy(this);
    }
}
