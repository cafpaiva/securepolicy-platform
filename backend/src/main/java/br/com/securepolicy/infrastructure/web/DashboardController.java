package br.com.securepolicy.infrastructure.web;

import br.com.securepolicy.application.dto.DashboardSummary;
import br.com.securepolicy.application.usecase.GetDashboardSummaryUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
public class DashboardController {

    private final GetDashboardSummaryUseCase getDashboardSummaryUseCase;

    public DashboardController(GetDashboardSummaryUseCase getDashboardSummaryUseCase) {
        this.getDashboardSummaryUseCase = getDashboardSummaryUseCase;
    }

    @GetMapping("/summary")
    public DashboardSummary summary() {
        return getDashboardSummaryUseCase.execute();
    }
}
