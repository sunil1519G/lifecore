package io.lifecore.finance.application.service;

import io.lifecore.finance.application.dto.PaginatedResponse;
import io.lifecore.finance.application.dto.TransactionDto;
import io.lifecore.finance.application.mapper.TransactionMapper;
import io.lifecore.finance.application.repository.TransactionRepository;
import io.lifecore.finance.domain.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionQueryService {

    private final TransactionRepository transactionRepository;

    public TransactionQueryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<TransactionDto> listTransactions(
        UUID accountId,
        OffsetDateTime from,
        OffsetDateTime to,
        String category,
        int page,
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        java.time.LocalDate fromDate = from != null ? from.toLocalDate() : null;
        java.time.LocalDate toDate = to != null ? to.toLocalDate() : null;
        Page<Transaction> transactionPage = transactionRepository.findByAccountIdAndFilters(
            accountId, fromDate, toDate, category, pageable
        );
        
        List<TransactionDto> items = transactionPage.getContent().stream()
            .map(TransactionMapper::toDto)
            .collect(Collectors.toList());
        
        return new PaginatedResponse<>(
            items,
            transactionPage.getNumber(),
            transactionPage.getSize(),
            transactionPage.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public TransactionDto getTransaction(UUID transactionId) {
        return transactionRepository.findById(transactionId)
            .map(TransactionMapper::toDto)
            .orElse(null);
    }
}
