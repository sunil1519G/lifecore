package io.lifecore.finance.application.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AccountDto(
    UUID accountId,
    String name,
    String type,
    String currency,
    String institution,
    Boolean active,
    OffsetDateTime createdAt
) {
}
