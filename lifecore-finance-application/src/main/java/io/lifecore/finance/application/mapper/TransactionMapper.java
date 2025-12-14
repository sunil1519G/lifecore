package io.lifecore.finance.application.mapper;

import io.lifecore.finance.application.dto.TransactionDto;
import io.lifecore.finance.domain.entity.Transaction;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TransactionMapper {

    private TransactionMapper() {
        // Utility class
    }

    public static TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        String categoryCode = transaction.getCategory() != null && transaction.getCategory().getCode() != null
            ? transaction.getCategory().getCode()
            : null;
        String currency = transaction.getAccount() != null
            ? transaction.getAccount().getCurrency()
            : null;
        OffsetDateTime date = transaction.getTxnDate() != null
            ? transaction.getTxnDate().atStartOfDay().atOffset(ZoneOffset.UTC)
            : null;
        return new TransactionDto(
            transaction.getId(),
            date,
            transaction.getDescription(),
            transaction.getAmount(),
            currency,
            categoryCode
        );
    }
}
