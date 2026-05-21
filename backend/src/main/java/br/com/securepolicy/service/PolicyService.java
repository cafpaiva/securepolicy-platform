package br.com.securepolicy.service;

import br.com.securepolicy.domain.ClaimStatus;
import br.com.securepolicy.domain.InsuranceClaim;
import br.com.securepolicy.domain.Policy;
import br.com.securepolicy.dto.*;
import br.com.securepolicy.repository.ClaimRepository;
import br.com.securepolicy.repository.PolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final ClaimRepository claimRepository;
    private final PolicyMapper mapper;

    public PolicyService(PolicyRepository policyRepository, ClaimRepository claimRepository, PolicyMapper mapper) {
        this.policyRepository = policyRepository;
        this.claimRepository = claimRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<PolicyResponse> listPolicies() {
        return policyRepository.findAll().stream()
                .map(mapper::toPolicyResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PolicyResponse getPolicy(Long id) {
        return mapper.toPolicyResponse(findPolicy(id));
    }

    @Transactional(readOnly = true)
    public List<ClaimResponse> listClaims() {
        return claimRepository.findAll().stream()
                .map(mapper::toClaimResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClaimResponse createClaim(CreateClaimRequest request) {
        Policy policy = findPolicy(request.getPolicyId());
        String protocol = "SIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        InsuranceClaim claim = new InsuranceClaim(
                protocol,
                request.getDescription(),
                request.getAmount(),
                ClaimStatus.OPEN,
                LocalDate.now()
        );

        policy.addClaim(claim);
        InsuranceClaim savedClaim = claimRepository.save(claim);
        return mapper.toClaimResponse(savedClaim);
    }

    @Transactional(readOnly = true)
    public DashboardSummary dashboardSummary() {
        long activePolicies = policyRepository.count();
        long openClaims = claimRepository.findByStatus(ClaimStatus.OPEN).size();
        long highRiskPolicies = policyRepository.countByRiskScoreGreaterThanEqual(75);

        return new DashboardSummary(
                activePolicies,
                openClaims,
                highRiskPolicies,
                policyRepository.sumPremium(),
                claimRepository.sumClaimAmount()
        );
    }

    private Policy findPolicy(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apolice nao encontrada: " + id));
    }
}
