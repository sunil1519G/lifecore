package io.lifecore.finance.application.service;

import io.lifecore.finance.application.dto.MonthlySummaryDto;
import io.lifecore.finance.application.repository.MonthlyOverallSummaryRepository;
import io.lifecore.finance.domain.entity.MonthlyOverallSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class SummaryQueryService {

    private final MonthlyOverallSummaryRepository monthlyOverallSummaryRepository;
    private static final String DEFAULT_CURRENCY = "INR";
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public SummaryQueryService(MonthlyOverallSummaryRepository monthlyOverallSummaryRepository) {
        this.monthlyOverallSummaryRepository = monthlyOverallSummaryRepository;
    }

    @Transactional(readOnly = true)
    public MonthlySummaryDto getMonthlySummary(String month) {
        YearMonth yearMonth = YearMonth.parse(month, MONTH_FORMATTER);
        LocalDate monthStart = yearMonth.atDay(1);
        
        return monthlyOverallSummaryRepository.findByMonth(monthStart)
            .map(this::toDto)
            .orElse(new MonthlySummaryDto(month, DEFAULT_CURRENCY, null, null));
    }

    private MonthlySummaryDto toDto(MonthlyOverallSummary summary) {
        String month = summary.getMonth().format(MONTH_FORMATTER);
        return new MonthlySummaryDto(
            month,
            DEFAULT_CURRENCY,
            summary.getTotalIncome(),
            summary.getTotalExpense()
        );
    }
}
