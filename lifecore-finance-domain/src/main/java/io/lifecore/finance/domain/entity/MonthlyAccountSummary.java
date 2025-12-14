package io.lifecore.finance.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "monthly_account_summary", indexes = {
    @Index(name = "idx_mas_month", columnList = "month")
})
@IdClass(MonthlyAccountSummaryId.class)
@Getter
@Setter
@NoArgsConstructor
public class MonthlyAccountSummary {

    @Id
    @Column(name = "month", nullable = false)
    private LocalDate month;

    @Id
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @Column(name = "total_inflow", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalInflow;

    @Column(name = "total_outflow", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalOutflow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyAccountSummary that = (MonthlyAccountSummary) o;
        return Objects.equals(month, that.month) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, accountId);
    }
}

