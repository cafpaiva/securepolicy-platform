package br.com.securepolicy.application.usecase;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.mapper.PolicyApplicationMapper;
import br.com.securepolicy.application.port.out.ClaimQueryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListClaimsUseCase {

    private final ClaimQueryPort claimQueryPort;
    private final PolicyApplicationMapper mapper;

    public ListClaimsUseCase(ClaimQueryPort claimQueryPort, PolicyApplicationMapper mapper) {
        this.claimQueryPort = claimQueryPort;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ClaimResponse> execute() {
        return claimQueryPort.findAll().stream()
                .map(mapper::toClaimResponse)
                .collect(Collectors.toList());
    }
}
