package io.lifecore.finance.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "subscription_summary")
@Getter
@Setter
@NoArgsConstructor
public class SubscriptionSummary {

    @Id
    @Column(name = "merchant_name", length = 150)
    private String merchantName;

    @Column(name = "monthly_average", nullable = false, precision = 14, scale = 2)
    private BigDecimal monthlyAverage;

    @Column(name = "transaction_count", nullable = false)
    private Integer transactionCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionSummary that = (SubscriptionSummary) o;
        return Objects.equals(merchantName, that.merchantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantName);
    }
}

