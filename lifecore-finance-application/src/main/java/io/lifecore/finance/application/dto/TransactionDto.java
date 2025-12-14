package io.lifecore.finance.application.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionDto(
    UUID transactionId,
    OffsetDateTime date,
    String description,
    BigDecimal amount,
    String currency,
    String category
) {
}
