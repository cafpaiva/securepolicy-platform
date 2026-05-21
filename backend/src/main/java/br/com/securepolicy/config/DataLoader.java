package br.com.securepolicy.config;

import br.com.securepolicy.domain.*;
import br.com.securepolicy.repository.PolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedDatabase(PolicyRepository policyRepository) {
        return args -> {
            Policy auto = new Policy(
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
            auto.addClaim(new InsuranceClaim(
                    "SIN-2938AB10",
                    "Colisao traseira em vistoria digital",
                    new BigDecimal("12400.00"),
                    ClaimStatus.UNDER_REVIEW,
                    LocalDate.now().minusDays(8)
            ));

            Policy home = new Policy(
                    "RES-2026-0042",
                    "Carlos Mendes",
                    "Residencial Completo",
                    PolicyStatus.PENDING_RENEWAL,
                    new BigDecimal("219.90"),
                    new BigDecimal("450000.00"),
                    LocalDate.now().minusMonths(11),
                    LocalDate.now().plusDays(20),
                    78
            );
            home.addClaim(new InsuranceClaim(
                    "SIN-83FA0291",
                    "Danos eletricos causados por oscilacao",
                    new BigDecimal("3800.00"),
                    ClaimStatus.OPEN,
                    LocalDate.now().minusDays(2)
            ));

            Policy life = new Policy(
                    "VIDA-2026-0108",
                    "Ana Paula Rocha",
                    "Vida Individual",
                    PolicyStatus.ACTIVE,
                    new BigDecimal("159.90"),
                    new BigDecimal("300000.00"),
                    LocalDate.now().minusMonths(3),
                    LocalDate.now().plusMonths(9),
                    24
            );

            policyRepository.save(auto);
            policyRepository.save(home);
            policyRepository.save(life);
        };
    }
}
