package br.com.securepolicy.infrastructure.protocol;

import br.com.securepolicy.application.port.out.ClaimProtocolGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidClaimProtocolGenerator implements ClaimProtocolGenerator {

    @Override
    public String nextProtocol() {
        return "SIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
