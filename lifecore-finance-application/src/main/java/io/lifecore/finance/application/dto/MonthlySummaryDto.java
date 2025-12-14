package io.lifecore.finance.application.dto;

import java.math.BigDecimal;

public record MonthlySummaryDto(
    String month,
    String currency,
    BigDecimal totalIncome,
    BigDecimal totalExpense
) {
}
