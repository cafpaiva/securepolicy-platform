package br.com.securepolicy.infrastructure.web;

import br.com.securepolicy.application.dto.ClaimResponse;
import br.com.securepolicy.application.dto.CreateClaimCommand;
import br.com.securepolicy.application.usecase.ListClaimsUseCase;
import br.com.securepolicy.application.usecase.OpenClaimUseCase;
import br.com.securepolicy.infrastructure.web.dto.CreateClaimRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class ClaimController {

    private final ListClaimsUseCase listClaimsUseCase;
    private final OpenClaimUseCase openClaimUseCase;

    public ClaimController(ListClaimsUseCase listClaimsUseCase, OpenClaimUseCase openClaimUseCase) {
        this.listClaimsUseCase = listClaimsUseCase;
        this.openClaimUseCase = openClaimUseCase;
    }

    @GetMapping
    public List<ClaimResponse> listClaims() {
        return listClaimsUseCase.execute();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimResponse createClaim(@Valid @RequestBody CreateClaimRequest request) {
        CreateClaimCommand command = new CreateClaimCommand(
                request.getPolicyId(),
                request.getDescription(),
                request.getAmount()
        );
        return openClaimUseCase.execute(command);
    }
}
