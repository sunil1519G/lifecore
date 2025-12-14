package io.lifecore.finance.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "monthly_overall_summary")
@Getter
@Setter
@NoArgsConstructor
public class MonthlyOverallSummary {

    @Id
    @Column(name = "month")
    private LocalDate month;

    @Column(name = "total_income", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalIncome;

    @Column(name = "total_expense", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalExpense;

    @Column(name = "net_balance", nullable = false, precision = 14, scale = 2)
    private BigDecimal netBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyOverallSummary that = (MonthlyOverallSummary) o;
        return Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
}

