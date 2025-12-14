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
@Table(name = "monthly_category_summary", indexes = {
    @Index(name = "idx_mcs_month", columnList = "month")
})
@IdClass(MonthlyCategorySummaryId.class)
@Getter
@Setter
@NoArgsConstructor
public class MonthlyCategorySummary {

    @Id
    @Column(name = "month", nullable = false)
    private LocalDate month;

    @Id
    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "total_spent", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalSpent;

    @Column(name = "transaction_count", nullable = false)
    private Integer transactionCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyCategorySummary that = (MonthlyCategorySummary) o;
        return Objects.equals(month, that.month) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, categoryId);
    }
}

