package io.lifecore.finance.web.controller;

import io.lifecore.finance.application.dto.PaginatedResponse;
import io.lifecore.finance.application.dto.TransactionDto;
import io.lifecore.finance.application.service.TransactionQueryService;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final TransactionQueryService transactionQueryService;

    public TransactionController(TransactionQueryService transactionQueryService) {
        this.transactionQueryService = transactionQueryService;
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<PaginatedResponse<TransactionDto>> listTransactions(
        @PathVariable UUID accountId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
        @RequestParam(required = false) String category,
        @RequestParam(required = false, defaultValue = "0") @Min(0) int page,
        @RequestParam(required = false, defaultValue = "50") @Min(1) int size
    ) {
        PaginatedResponse<TransactionDto> response = transactionQueryService.listTransactions(
            accountId, from, to, category, page, size
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable UUID transactionId) {
        TransactionDto transaction = transactionQueryService.getTransaction(transactionId);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }
}
