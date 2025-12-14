package io.lifecore.finance.web.controller;

import io.lifecore.finance.application.dto.MonthlySummaryDto;
import io.lifecore.finance.application.service.SummaryQueryService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/summary")
public class SummaryController {

    private final SummaryQueryService summaryQueryService;

    public SummaryController(SummaryQueryService summaryQueryService) {
        this.summaryQueryService = summaryQueryService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlySummaryDto> getMonthlySummary(
        @RequestParam @Pattern(regexp = "\\d{4}-\\d{2}", message = "Month must be in format yyyy-MM") String month
    ) {
        MonthlySummaryDto summary = summaryQueryService.getMonthlySummary(month);
        return ResponseEntity.ok(summary);
    }
}
